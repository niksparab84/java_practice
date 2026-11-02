package org.nikhil.examples.dsaChallenges;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * https://leetcode.com/problems/design-an-atm-machine/
 * There is an ATM machine that stores banknotes of 5 denominations: 20, 50, 100, 200, and 500 dollars. Initially the ATM is empty. The user can use the machine
 * to deposit or withdraw any amount of money. When withdrawing, the machine prioritizes using banknotes of larger values.
 * For example, if you want to withdraw $300 and there are 2 $50 banknotes, 1 $100 banknote, and 1 $200 banknote, then the machine will use the $100 and $200 banknotes.
 * However, if you try to withdraw $600 and there are 3 $200 banknotes and 1 $500 banknote, then the withdraw request will be rejected because the machine will first
 * try to use the $500 banknote and then be unable to use banknotes to complete the remaining $100. Note that the machine is not allowed to use the $200 banknotes
 * instead of the $500 banknote.
 *
 * Implement the ATM class:
 * ATM() Initializes the ATM object.
 * void deposit(int[] banknotesCount) Deposits new banknotes in the order $20, $50, $100, $200, and $500.
 * int[] withdraw(int amount) Returns an array of length 5 of the number of banknotes that will be handed to the user in the order $20, $50, $100, $200, and $500,
 * and update the number of banknotes in the ATM after withdrawing. Returns [-1] if it is not possible (do not withdraw any banknotes in this case).
 */

public class ATMMachineLeetCode {

    private static final int[] DENOMINATIONS = {20, 50, 100, 200, 500};
    private List<int[]> resultList;
    private int[] totalATMDenominations;
    private int[] noTransaction;

    public ATMMachineLeetCode() {
        resultList = new ArrayList<>();
        totalATMDenominations = new int[] {0, 0, 0, 0, 0};
        noTransaction = new int[] {-1};
    }

    public void deposit(int[] banknotesCount) {
        for (int i = 0; i < banknotesCount.length; i++) {
            totalATMDenominations[i] += banknotesCount[i];
        }
    }

    public int[] withdraw(int amount) {
        int[] withdrawalDenominations = new int[5];
        if(canWithdraw(amount)) {
            for(int d = DENOMINATIONS.length - 1; d >= 0; d--) {
                if(amount >= DENOMINATIONS[d]) {
                    int requiredNotes = Math.abs(amount /  DENOMINATIONS[d]);
                    if(requiredNotes > 0 && requiredNotes <= totalATMDenominations[d]) {
                        // we can do withdrawal
                        totalATMDenominations[d] -= requiredNotes;
                        amount = amount - requiredNotes * DENOMINATIONS[d];
                        withdrawalDenominations[d] = requiredNotes;
                    } else {
                        // if higher denomination can't cater withdrawal then no transaction
                        withdrawalDenominations = noTransaction;
                        break;
                    }
                }  else {
                    // Withdrawal amt is less than denomination, hence 0 notes
                    withdrawalDenominations[d] = 0;
                }
            }
        } else {
            withdrawalDenominations = noTransaction;
        }

        return withdrawalDenominations;
    }

    public boolean canWithdraw(int amount) {
        boolean canWithdraw = true;
        for(int d = DENOMINATIONS.length - 1; d >= 0; d--) {
            if(amount >= DENOMINATIONS[d]) {
                int requiredNotes = Math.abs(amount / DENOMINATIONS[d]);
                if (requiredNotes > 0 && requiredNotes <= totalATMDenominations[d]) {
                    amount = amount - requiredNotes * DENOMINATIONS[d];
                } else {
                    canWithdraw = false;
                }
            }
        }
        if(amount > 0) {
            canWithdraw = false;
        }
        return canWithdraw;
    }

    public static void main(String[] args) {
        ATMMachineLeetCode atm = new ATMMachineLeetCode();
        atm.deposit(new int[] {0,10,0,3,0});
        int[] withdrawalDenominations = atm.withdraw(500);
        System.out.println(Arrays.toString(withdrawalDenominations));
    }
}

/**
 * Example 1:
 * Input
 * ["ATM", "deposit", "withdraw", "deposit", "withdraw", "withdraw"]
 * [[], [[0,0,1,2,1]], [600], [[0,1,0,1,1]], [600], [550]]
 * Output
 * [null, null, [0,0,1,0,1], null, [-1], [0,1,0,0,1]]
 *
 * Explanation
 * ATM atm = new ATM();
 * atm.deposit([0,0,1,2,1]); // Deposits 1 $100 banknote, 2 $200 banknotes,
 *                           // and 1 $500 banknote.
 * atm.withdraw(600);        // Returns [0,0,1,0,1]. The machine uses 1 $100 banknote
 *                           // and 1 $500 banknote. The banknotes left over in the
 *                           // machine are [0,0,0,2,0].
 * atm.deposit([0,1,0,1,1]); // Deposits 1 $50, $200, and $500 banknote.
 *                           // The banknotes in the machine are now [0,1,0,3,1].
 * atm.withdraw(600);        // Returns [-1]. The machine will try to use a $500 banknote
 *                           // and then be unable to complete the remaining $100,
 *                           // so the withdraw request will be rejected.
 *                           // Since the request is rejected, the number of banknotes
 *                           // in the machine is not modified.
 * atm.withdraw(550);        // Returns [0,1,0,0,1]. The machine uses 1 $50 banknote
 *                           // and 1 $500 banknote.
 */