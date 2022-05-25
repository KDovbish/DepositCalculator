import java.math.BigDecimal;

/**
 * ����� ���������� �������� � ������� �� �����
 * �������������(���������� % � ����� ������ + ���������� ��������� �� ��������) ���� ������ �����
 * @param args ����� ������, ������� �������, ������������ ������ � �����
 */
public class DepositCalculator {
    public static void main(String[] args) {

        if (args.length!=3) {
            System.out.println("Syntax:  DepositCalculator <����� ������> <������� �������> <������������ ������ � �����>");
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
                //  ����� ����������� ��������� �� ������� �����
                monthPercentSum=getMonthPercentSum(newSumma, yearPercent);
                //  ������� ����� ����� ����������� ��������� �� ���
                yearPercentSum=addition(yearPercentSum, monthPercentSum);
                //  �������������(����������) �������� ��������� � ����� ������
                newSumma=addition(newSumma, monthPercentSum);

            }
            System.out.println("��� " + year + "  ����������� �����: " + newSumma + "  ����� % �� ���: " + yearPercentSum);
        }
    }

    /**
     * �������� ����� ��������� �� ����� ������ �� ����� ������ � ������� ���������� ������
     * ��������� ��������� "��������" ����������� �� ���� ������ ����� �������
     * @param summa ����� ������
     * @param yearPercent ������� ���������� ������
     * @return ����� ����������� ��������� �� �����
     */
    static double getMonthPercentSum(double summa, double yearPercent) {
        return new BigDecimal(summa*yearPercent/100/12).setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue();
    }

    /**
     * �������� ���� double-�������� � "��������" ����������� �� ���� ������ ����� �������     *
     */
    static double addition(double a, double b) {
        return new BigDecimal(a+b).setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue();
    }
}
