import java.math.BigDecimal;

/**
 * Суммы накоплений отдаются в разрезе по годам
 * Капитализация(добавление % к сумма вклада + начисление процентов на проценты) идет каждый месяц
 * @param args Сумма вклада, Годовой процент, Длительность вклада в годах
 */
public class DepositCalculator {
    public static void main(String[] args) {

        if (args.length!=3) {
            System.out.println("Syntax:  DepositCalculator <Сумма вклада> <Годовой процент> <Длительность вклада в годах>");
            return;
        }

        double summa=Double.valueOf(args[0]).doubleValue();
        double yearPercent=Double.valueOf(args[1]).doubleValue();
        int yearsNumber=Integer.valueOf(args[2]).intValue();

        double monthPercentSum=0;
        double yearPercentSum=0;
        double newSumma=summa;
        for (int year=1; year<=yearsNumber; year++) {
            yearPercentSum=0;
            for (int month=1; month<=12; month++) {
                //  Сумма начисленных процентов за текущий месяц
                monthPercentSum=getMonthPercentSum(newSumma, yearPercent);
                //  Подсчет общей суммы начисленных процентов за год
                yearPercentSum=addition(yearPercentSum, monthPercentSum);
                //  Капитализация(добавление) месячных процентов в сумму вклада
                newSumma=addition(newSumma, monthPercentSum);

            }
            System.out.println("Год " + year + "  Накопленная сумма: " + newSumma + "  Сумма % за год: " + yearPercentSum);
        }
    }

    /**
     * Получить сумму процентов за месяц исходя из суммы вклада и годовой процентной ставки
     * Результат округлить "денежным" округлением до двух знаков после запятой
     * @param summa Сумма вклада
     * @param yearPercent Годовая процентная ставка
     * @return Сумма начисленных процентов за месяц
     */
    static double getMonthPercentSum(double summa, double yearPercent) {
        return new BigDecimal(summa*yearPercent/100/12).setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue();
    }

    /**
     * Сложение двух double-значение с "денежным" огруглением до двух знаков после запятой     *
     */
    static double addition(double a, double b) {
        return new BigDecimal(a+b).setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue();
    }
}
