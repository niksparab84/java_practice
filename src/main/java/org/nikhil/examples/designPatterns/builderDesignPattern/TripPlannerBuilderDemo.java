package org.nikhil.examples.designPatterns.builderDesignPattern;

import java.util.Date;

enum SeatPreference {
    AISLE,
    WINDOW,
    MIDDLE
}

class TripPlan {
    private final String origin;
    private final String destination;
    private final Date departureDate;
    private final Date returnDate;
    private final boolean hotelIncluded;
    private final boolean airportTransferIncluded;
    private final SeatPreference seatingPreference;
    private final boolean insuranceIncluded;

    // private constructor to enforce the use of the builder
    private TripPlan(Builder builder) {
        this.origin = builder.origin;
        this.destination = builder.destination;
        this.departureDate = builder.departureDate;
        this.returnDate = builder.returnDate;
        this.hotelIncluded = builder.hotelIncluded;
        this.airportTransferIncluded = builder.airportTransferIncluded;
        this.seatingPreference = builder.seatingPreference;
        this.insuranceIncluded = builder.insuranceIncluded;
    }

    public Builder toBuilder() {
        return new Builder(this);
    }

    // static nested builder class
    public static class Builder {
        private final String origin;
        private final String destination;
        private Date departureDate;
        private Date returnDate;
        private boolean hotelIncluded;
        private boolean airportTransferIncluded;
        private SeatPreference seatingPreference;
        private boolean insuranceIncluded;

        private Builder(TripPlan tripPlan) {
            this.origin = tripPlan.origin;
            this.destination = tripPlan.destination;
            this.departureDate = copyDate(tripPlan.departureDate);
            this.returnDate = copyDate(tripPlan.returnDate);
            this.hotelIncluded = tripPlan.hotelIncluded;
            this.airportTransferIncluded = tripPlan.airportTransferIncluded;
            this.seatingPreference = tripPlan.seatingPreference;
            this.insuranceIncluded = tripPlan.insuranceIncluded;
        }

        public Builder(String origin, String destination) {
            this.origin = origin;
            this.destination = destination;

            //validation
            if(this.origin.equalsIgnoreCase(this.destination)){
                throw new IllegalArgumentException("Origin and destination cannot be the same");
            }
        }

        public Builder withDepartureDate(Date departureDate) {
            this.departureDate = departureDate;
            return this;
        }

        public Builder withReturnDate(Date returnDate) {
            this.returnDate = returnDate;
            return this;
        }

        public Builder withHotelIncluded(boolean hotelIncluded) {
            this.hotelIncluded = hotelIncluded;
            return this;
        }

        public Builder withAirportTransferIncluded(boolean airportTransferIncluded) {
            this.airportTransferIncluded = airportTransferIncluded;
            return this;
        }

        public Builder withSeatingPreference(SeatPreference seatingPreference) {
            this.seatingPreference = seatingPreference;
            return this;
        }

        public Builder withInsuranceIncluded(boolean insuranceIncluded) {
            this.insuranceIncluded = insuranceIncluded;
            return this;
        }

        public TripPlan build() {
            return new TripPlan(this);
        }
    }

    @Override
    public String toString() {
        return "TripPlan{" +
                "origin='" + origin + '\'' +
                ", destination='" + destination + '\'' +
                ", departureDate=" + departureDate +
                ", returnDate=" + returnDate +
                ", hotelIncluded=" + hotelIncluded +
                ", airportTransferIncluded=" + airportTransferIncluded +
                ", seatingPreference=" + seatingPreference +
                ", insuranceIncluded=" + insuranceIncluded +
                '}';
    }

    private static Date copyDate(Date date) {
        return date == null ? null : new Date(date.getTime());
    }
}

//Use the builder to create a TripPlan object
public class TripPlannerBuilderDemo {
    public static void main(String[] args) {
        TripPlan tripPlan = new TripPlan.Builder("Mumbai", "NYC")
                .withDepartureDate(new Date())
                .withReturnDate(new Date()) // 5 days later
                .withHotelIncluded(true)
                .withAirportTransferIncluded(true)
                .withSeatingPreference(SeatPreference.AISLE)
                .withInsuranceIncluded(true)
                .build();

        System.out.println(tripPlan);

        TripPlan updatedTripPlan = tripPlan.toBuilder()
                .withSeatingPreference(SeatPreference.WINDOW)
                .build();
        System.out.println(updatedTripPlan);
    }
}
