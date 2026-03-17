package ru.netology.javaqadiplom;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class CreditAccountTest {

    //Тесты на пополнение карты на указанную сумму
    @Test //пополнение нулевого начального баланса
    public void shouldAddToPositiveBalance() {
        CreditAccount account = new CreditAccount(
                0,
                5_000,
                15
        );

        account.add(3_000);
        Assertions.assertEquals(3_000, account.getBalance());
    }

    @Test //пополнение ненулевого начального баланса
    public void shouldReplenishmentOfNonZeroBalance() {
        CreditAccount account = new CreditAccount(
                5_000,
                5_000,
                15
        );

        account.add(3_000);
        Assertions.assertEquals(8_000, account.getBalance());
    }

    @Test //пополнение начального баланса на отрицательную сумму
    public void shouldAddToNegativeBalance() {
        CreditAccount account = new CreditAccount(
                0,
                5_000,
                15
        );

        Assertions.assertEquals(false, account.add(-3_000));
    }

    @Test //пополнение начального баланса нулевой суммой
    public void shouldAddToZeroBalance() {
        CreditAccount account = new CreditAccount(
                0,
                5_000,
                15
        );

        Assertions.assertEquals(false, account.add(0));
    }

    //Тесты на выкидывание исключения вида IllegalArgumentException
    @Test //создание кредитного счета с отрицательным начальным балансом
    public void shouldCreateCreditAccountNegativeInitialBalance() {
        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            CreditAccount account = new CreditAccount(
                    -1_000,
                    5_000,
                    15
            );
        });
    }

    @Test //создание кредитного счета с отрицательным кредитным лимитом
    public void shouldCreateCreditAccountNegativeCreditLimit() {
        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            CreditAccount account = new CreditAccount(
                    0,
                    -5_000,
                    15
            );
        });
    }

    @Test //создание кредитного счета с отрицательной ставкой
    public void shouldCreateCreditAccountNegativeRate() {
        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            CreditAccount account = new CreditAccount(
                    0,
                    5_000,
                    -5
            );
        });
    }

    //Тест на создание кредитного счета с нулевыми параметрами
    @Test //создание кредитного счета с нулевыми значениями начального баланса, кредитного лимита и процентной ставкой
    public void shouldCreateCreditAccountZeroRate() {
            CreditAccount account = new CreditAccount(
                    0,
                    0,
                    0
            );
    }

    //Тесты на оплату с карты на указанную сумму
    @Test //покупка на отрицательную сумму
    public void shouldPayNegativeAmount() {
        CreditAccount account = new CreditAccount(
                1_000,
                1_000,
                10
        );

        Assertions.assertEquals(false, account.pay(-500));
    }

    @Test //покупка на нулевую сумму
    public void shouldPayZeroAmount() {
        CreditAccount account = new CreditAccount(
                1_000,
                1_000,
                10
        );

        Assertions.assertEquals(false, account.pay(0));
    }

    @Test //покупка на сумму в пределах положительного баланса
    public void shouldPayPositiveAmount() {
        CreditAccount account = new CreditAccount(
                1_000,
                1_000,
                10
        );

        account.pay(200);
        Assertions.assertEquals(800, account.getBalance());
    }

    @Test //покупка на сумму равную балансу
    public void shouldPayEqualToBalance() {
        CreditAccount account = new CreditAccount(
                1_000,
                1_000,
                10
        );

        account.pay(1_000);
        Assertions.assertEquals(0, account.getBalance());
    }

    @Test //покупка в пределах кредитного лимита
    public void shouldPayPositiveCreditLimit() {
        CreditAccount account = new CreditAccount(
                1_000,
                2_000,
                10
        );

        account.pay(2_000);
        Assertions.assertEquals(-1_000, account.getBalance());
    }

    @Test //покупка на сумму больше кредитного лимита
    public void shouldPayNegativeCreditLimit() {
        CreditAccount account = new CreditAccount(
                1_000,
                2_000,
                10
        );

        Assertions.assertEquals(false, account.pay(4_000));
    }

    @Test //покупка равна сумме баланса и кредитного лимита
    public void shouldPayEqualSumBalanceAndCreditLimit() {
        CreditAccount account = new CreditAccount(
                1_000,
                4_000,
                10
        );

        account.pay(5_000);
        Assertions.assertEquals(-4_000, account.getBalance());
    }

    //Тесты на расчёт процентов на отрицательный баланс счёта
    @Test //Расчёт процентной ставки при положительном балансе
    public void shouldYearChangePositiveBalance() {
        CreditAccount account = new CreditAccount(
                200,
                1_000,
                15
        );

        Assertions.assertEquals(0, account.yearChange());
    }

    @Test //Расчёт процентной ставки при отрицательном балансе
    public void shouldYearChangeNegativeBalance() {
        CreditAccount account = new CreditAccount(
                0,
                1_000,
                15
        );

        account.pay(-200);
        Assertions.assertEquals(-30, account.yearChange());
    }

}
