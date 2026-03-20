package ru.netology.javaqadiplom;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class SavingAccountTest {

    @Test
    public void shouldAddLessThanMaxBalance() { //  не прошел
        SavingAccount account = new SavingAccount(
                2_000,
                1_000,
                10_000,
                5
        );

        account.add(3_000);

        Assertions.assertEquals(2_000 + 3_000, account.getBalance());
    }

    @Test
    public void rateExceptionTest() { //прошел

        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            SavingAccount account = new SavingAccount(
                    2_000,
                    1_000,
                    10_000,
                    -5
            );
        });
    }

    @Test
    public void initialBalanceExceptionTest() /*отрицательный баланс*/ { //не прошел

        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            SavingAccount account = new SavingAccount(
                    -2_000,
                    1_000,
                    10_000,
                    5
            );
        });
    }

    @Test
    public void minBalanceExceptionTest() /*отрицательный минимум*/ { //не прошел

        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            SavingAccount account = new SavingAccount(
                    2_000,
                    -1_000,
                    10_000,
                    5
            );
        });
    }

    @Test
    public void maxLessMinExceptionTest() /*максимум меньше минимум*/ { //не прошел

        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            SavingAccount account = new SavingAccount(
                    2_000,
                    1_000,
                    100,
                    5
            );
        });
    }

    @Test
    public void balanceLessMinExceptionTest() /*баланс меньше минимум*/ { //не прошел

        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            SavingAccount account = new SavingAccount(
                    500,
                    1_000,
                    10_000,
                    5
            );
        });
    }

    @Test
    public void balanceMoreMaxExceptionTest() /*баланс больше максимум*/ { //не прошел

        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            SavingAccount account = new SavingAccount(
                    13_000,
                    1_000,
                    10_000,
                    5
            );
        });
    }

    // ********payTest********

    @Test
    public void negativePayTest() { //прошел
        SavingAccount account = new SavingAccount(3_000, 1_000, 10_000, 5);

        Assertions.assertFalse(account.pay(-1000));
    }

    @Test
    public void balanceNegativePayTest() { //прошел
        SavingAccount account = new SavingAccount(3_000, 1_000, 10_000, 5);
        account.pay(-1000);

        Assertions.assertEquals(3_000, account.getBalance());

    }

    @Test
    public void positivePayToMinTest() { //прошел
        SavingAccount account = new SavingAccount(3_000, 1_000, 10_000, 5);

        Assertions.assertTrue(account.pay(500));
    }

    @Test
    public void balancePositivePayToMinTest() { //прошел
        SavingAccount account = new SavingAccount(3_000, 1_000, 10_000, 5);
        account.pay(200);

        Assertions.assertEquals(3_000 - 200, account.getBalance());
    }

    @Test
    public void positivePaylessMinTest() { //прошел
        SavingAccount account = new SavingAccount(3_000, 1_000, 10_000, 5);

        Assertions.assertFalse(account.pay(4_100));
    }

    @Test
    public void balancePositivePayLessMinTest() { //не прошел
        SavingAccount account = new SavingAccount(3_000, 1_000, 10_000, 5);
        account.pay(2_500);

        Assertions.assertEquals(3_000, account.getBalance());
    }

    // ********addTest********

    @Test
    public void negativeAddTest() { // прошел
        SavingAccount account = new SavingAccount(3_000, 1_000, 10_000, 5);

        Assertions.assertFalse(account.add(-1_000));
    }

    @Test
    public void balanceNegativeAddTest() { // прошел
        SavingAccount account = new SavingAccount(3_000, 1_000, 10_000, 5);
        account.add(-1_000);

        Assertions.assertEquals(3_000, account.getBalance());
    }

    @Test
    public void positiveAddToMaxTest() { //прошел
        SavingAccount account = new SavingAccount(3_000, 1_000, 10_000, 5);

        Assertions.assertTrue(account.add(1_000));
    }

    @Test
    public void balancePositiveAddSuccessTest() { // не прошел
        SavingAccount account = new SavingAccount(3_000, 1_000, 10_000, 5);
        account.add(1_000);

        Assertions.assertEquals(3_000 + 1_000, account.getBalance());
    }

    @Test
    public void positiveAddMoreMaxTest() { //прошел
        SavingAccount account = new SavingAccount(3_000, 1_000, 10_000, 5);

        Assertions.assertFalse(account.add(11_000));
    }

    @Test
    public void balancePositiveAddFailTest() { //прошел
        SavingAccount account = new SavingAccount(3_000, 1_000, 10_000, 5);
        account.add(11_000);

        Assertions.assertEquals(3_000, account.getBalance());
    }

    // ********yearChangeTest********

    @Test
    public void noChangesForYearInterestOnBalanceTest() { //прошел
        SavingAccount account = new SavingAccount(3_000, 1_000, 10_000, 5);

        Assertions.assertEquals(3_000 / 100 * 5, account.yearChange());
    }

    @Test
    public void roundingOfInterestOnDepositTest() { //прошел
        SavingAccount account = new SavingAccount(3_211, 1_000, 10_000, 5);

        Assertions.assertEquals(3_211 / 100 * 5, account.yearChange());
    }

}
