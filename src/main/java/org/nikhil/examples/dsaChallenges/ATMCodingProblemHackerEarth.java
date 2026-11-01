package org.nikhil.examples.dsaChallenges;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * ATM Machine Coding Problem - HackerEarth - Barclays
 * Input will be like
 * 3
 * -1 0 1 1 1 1
 * 600 -1 -1 -1 -1 -1
 * 250 -1 -1 -1 -1 -1
 * starting with -1 is deposit else withdraw
 *
 * Output:
 * for deposit it is blank
 * withdrawal output will be array of denominations.
 *
 * sample output:
 * 0 0 1 0 1
 * 0 1 0 1 0
 *
 * Denominations in ATM: 20 50 100 200 500
 * When withdrawing, the machine prioritizes using banknotes of larger values.
 */
public class ATMCodingProblemHackerEarth {

    private static final int[] DENOMINATIONS = {20, 50, 100, 200, 500};

    public static int[][] makeATMTransaction(int N, int[][] transactions) {
        int[][] result = new int[N][5];
        List<int[]> resultList = new ArrayList<>();
        int[] totalATMDenominations = {0, 0, 0, 0, 0};
        int[] noTransaction = {-1, -1, -1, -1, -1};

        for (int i = 0; i < N; i++) {
            int transactionAmt = transactions[i][0];

            if(transactionAmt == -1) {
                // deposit
                for(int j = 1; j <= 5; j++) {
                    totalATMDenominations[j-1] += transactions[i][j];
                }
                System.out.println("Total ATM Denominations on deposit: " + Arrays.toString(totalATMDenominations));
            } else {
                // withdrawal
                int[] withdrawalDenominations = new int[5];
                for(int d = DENOMINATIONS.length - 1; d >= 0; d--) {
                    if(transactionAmt >= DENOMINATIONS[d]) {
                        int requiredNotes = Math.abs(transactionAmt / DENOMINATIONS[d] );
                        if(requiredNotes > 0 && requiredNotes <= totalATMDenominations[d]) {
                            //we can do withdrawal
                            totalATMDenominations[d] -= requiredNotes;
                            transactionAmt = transactionAmt - requiredNotes * DENOMINATIONS[d];
                            withdrawalDenominations[d] = requiredNotes;
                        } else {
                            // if higher denomination can't cater withdrawal then no tranaction
                            withdrawalDenominations = noTransaction;
                            break;
                        }
                    } else {
                        // Withdrawal amt is less then denomination, hence 0 notes
                        withdrawalDenominations[d] = 0;
                    }
                }
                //result[i] = withdrawalDenominations;
                resultList.add(withdrawalDenominations);
            }
        }
        System.out.println("Total ATM Denominations on transaction: " + Arrays.toString(totalATMDenominations));
        return resultList.toArray(new int[0][]);
    }

    public static void main(String[] args) {
        //Scanner in = new Scanner(System.in);
        //int N = in.nextInt();

        //hardcode inputs
        int N = 3;
        int[][] transactions = new int[N][6];
        // -1 0 1 1 1 1
        //600 -1 -1 -1 -1 -1
        //250 -1 -1 -1 -1 -1
        //int[] trans1 = {-1, 0, 1, 1, 1, 1};
        //int[] trans1 = {-1, 0, 1, 0, 1, 1};
        int[] trans1 = {-1, 0, 1, 3, 0, 1};
        transactions[0] = trans1;
        int[] trans2 = {600, -1, -1, -1, -1, -1};
        transactions[1] = trans2;
        int[] trans3 = {250, -1, -1, -1, -1, -1};
        transactions[2] = trans3;

        //print transactions
        System.out.println("transactions: ");
        System.out.println(Arrays.deepToString(transactions));

        int[][] output = makeATMTransaction(N, transactions);
        System.out.println("output: ");
        System.out.println(Arrays.deepToString(output));
    }
}
