package history;

import java.io.Serializable;
import java.math.BigDecimal;

public class HistoryNode implements Serializable {
    private String x;
    private String y;
    private String r;

    private boolean hit;

    public String getX() {
        return x;
    }

    public String getY() {
        return y;
    }

    public String getR() {
        return r;
    }

    public HistoryNode(String x, String y, String r) throws Exception {
        this.x = x;
        this.y = y;
        this.r = r;

        try {
            BigDecimal bigX = new BigDecimal(x);
            BigDecimal bigY = new BigDecimal(y);
            BigDecimal bigR = new BigDecimal(r);

            if ( bigX.abs().compareTo( BigDecimal.valueOf(3) ) >= 0 ) // x > 3 or x < -3
                throw new Exception("Некорректное значение X");
            if ( bigY.abs().compareTo( BigDecimal.valueOf(5) ) >= 0 ) // y > 5 or y < -5
                throw new Exception("Некорректное значение Y");
            if ( (bigR.compareTo(BigDecimal.valueOf(4)) >= 0) || (bigR.compareTo(BigDecimal.valueOf(1)) <= 0) ) // r > 4 OR r < 1
                throw new Exception("Некорректное значение R");

            this.hit = calculateHit(bigX, bigY, bigR);

        } catch (NumberFormatException exc) {
            throw new Exception("Один из аргументов не является десятичным числом.");
        }
    }

    public boolean isHit() {
        return hit;
    }

    private boolean calculateHit(BigDecimal x, BigDecimal y, BigDecimal r) {
        if (( (x.compareTo(BigDecimal.ZERO) <= 0) &&
                (y.compareTo(BigDecimal.ZERO) >= 0) &&
                (x.pow(2).add(y.pow(2)).compareTo(r.pow(2)) <=0))

                ||( (x.compareTo(BigDecimal.ZERO) >=0) &&
                (y.compareTo(BigDecimal.ZERO) <=0) &&
                (x.compareTo(r) <=0) &&
                (y.compareTo(r.negate()) >=0))

                ||( (x.compareTo(BigDecimal.ZERO) >= 0) &&
                (y.compareTo(BigDecimal.ZERO) >= 0) &&
                (y.compareTo(r.subtract(x)) <= 0) ))
        {
            return true;
        } else {
            return false;
        }

    }
}
