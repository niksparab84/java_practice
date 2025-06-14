package org.nikhil.examples.systemDesignQuestions;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicLong;
import java.util.concurrent.atomic.AtomicReference;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
import java.util.function.Consumer;
import java.util.function.Supplier;
import org.nikhil.examples.systemDesignQuestions.DatabaseWriteRequest;
import org.nikhil.examples.systemDesignQuestions.DatabaseWriteResponse;
import org.nikhil.examples.systemDesignQuestions.DatabaseWriteStatus;
import org.nikhil.examples.systemDesignQuestions.WriteRequestStatus;
import org.nikhil.examples.systemDesignQuestions.WriteResponseStatus;

class DatabaseWriteRequest {
    private final String id;
    private final String data;

    public DatabaseWriteRequest(String id, String data) {
        this.id = id;
        this.data = data;
    }

    public String getId() {
        return id;
    }

    public String getData() {
        return data;
    }
}

class DatabaseWriteResponse {
    private final WriteResponseStatus status;
    private final DatabaseWriteStatus writeStatus;

    public DatabaseWriteResponse(WriteResponseStatus status, DatabaseWriteStatus writeStatus) {
        this.status = status;
        this.writeStatus = writeStatus;
    }

    public WriteResponseStatus getStatus() {
        return status;
    }

    public DatabaseWriteStatus getWriteStatus() {
        return writeStatus;
    }
}

class DatabaseWriteStatus {
    private final String requestId;
    private final WriteRequestStatus status;

    public DatabaseWriteStatus(String requestId, WriteRequestStatus status) {
        this.requestId = requestId;
        this.status = status;
    }

    public String getRequestId() {
        return requestId;
    }

    public WriteRequestStatus getStatus() {
        return status;
    }
}

class WriteRequestStatus {
    public static final WriteRequestStatus COMPLETED = new WriteRequestStatus("COMPLETED");
    public static final WriteRequestStatus FAILED = new WriteRequestStatus("FAILED");

    private final String status;

    private WriteRequestStatus(String status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return status;
    }
}

class WriteResponseStatus {
    public static final WriteResponseStatus SUCCESS = new WriteResponseStatus("SUCCESS");
    public static final WriteResponseStatus FAILED = new WriteResponseStatus("FAILED");
    public static final WriteResponseStatus NO_REQUEST = new WriteResponseStatus("NO_REQUEST");

    private final String status;

    private WriteResponseStatus(String status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return status;
    }
}

public class DatabaseWriteQueue {

    private final BlockingQueue<DatabaseWriteRequest> writeQueue;
    private final AtomicBoolean isRunning;
    private final AtomicInteger writeCount;
    private final AtomicLong totalWriteTime;
    private final Lock lock;

    public DatabaseWriteQueue(int capacity) {
        this.writeQueue = new LinkedBlockingQueue<>(capacity);
        this.isRunning = new AtomicBoolean(true);
        this.writeCount = new AtomicInteger(0);
        this.totalWriteTime = new AtomicLong(0);
        this.lock = new ReentrantLock();
    }

    public void addWriteRequest(DatabaseWriteRequest request) {
        if (isRunning.get()) {
            writeQueue.offer(request);
        }
    }

    public DatabaseWriteResponse processNextWrite() {
        DatabaseWriteRequest request = writeQueue.poll();
        if (request == null) {
            return new DatabaseWriteResponse(WriteResponseStatus.NO_REQUEST, null);
        }

        long startTime = System.nanoTime();
        try {
            // Simulate database write operation
            Thread.sleep(100); // Simulated delay for the write operation
            long endTime = System.nanoTime();
            long duration = endTime - startTime;

            lock.lock();
            try {
                writeCount.incrementAndGet();
                totalWriteTime.addAndGet(duration);
            } finally {
                lock.unlock();
            }

            return new DatabaseWriteResponse(WriteResponseStatus.SUCCESS,
                    new DatabaseWriteStatus(request.getId(), WriteRequestStatus.COMPLETED));
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            return new DatabaseWriteResponse(WriteResponseStatus.FAILED,
                    new DatabaseWriteStatus(request.getId(), WriteRequestStatus.FAILED));
        }
    }

    public int getTotalWrites() {
        return writeCount.get();
    }

    public long getAverageWriteTime() {
        return writeCount.get() == 0 ? 0 : totalWriteTime.get() / writeCount.get();
    }

    public void shutdown() {
        isRunning.set(false);
    }
}
