package jp.co.isid.mos.bird.portal.login.logic.impl;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

import jp.co.isid.mos.bird.framework.exception.ApplicationException;
import jp.co.isid.mos.bird.portal.login.entity.MatrixEntity;
import jp.co.isid.mos.bird.portal.login.logic.MakeMatrixLogic;


/**
 * @author xnkusama
 */
public class MakeMatrixLogicImpl implements MakeMatrixLogic {

    /* ロジックＩＤ*/ 
    public static final String LOGIC_ID = "BPO000L04";
    
    public static final String SHA1PRNG = "SHA1PRNG";
    public static final String MD5 = "MD5";

    /* セッションキー作成処理 */
    public List makeMatrixKey() throws ApplicationException {
        
        List matrixList = new ArrayList();
        
        // 生成乱数のMAX値
        BigInteger max = new BigInteger("256");  
        Random rnd = new Random();

        // ボタンラベル用のListを作成
        List listButtonLabel = new ArrayList();
        for (int i = 1; i < 100; i++) {
            listButtonLabel.add(String.valueOf(i));
        }
        // ボタンラベルListをランダム
        Collections.shuffle(listButtonLabel);
        
        for (int i = 1; i <= 5; i++) {
            MatrixEntity entity = new MatrixEntity();
            for (int j = 1; j <= 5; j++) {
                BigInteger num = generate(max, rnd);
                entity.setValue(j-1, asHex(num.toString()));
                entity.setButtonLabel(j-1, (String) listButtonLabel.get((i-1) * 5 + j));
            }
            matrixList.add(entity);
        }
        
        return matrixList;
    }

    /**
     * 0以上かつ指定された値未満の整数値をランダムに生成する．クラスメソッド．
     * @param max 最大値
     * @param rnd 乱数
     * @return 乱数値
     */
    private static BigInteger generate(BigInteger max, Random rnd) {
      int bit;
      BigInteger tmp = max;
      BigInteger num;

      for (bit = 1; tmp.compareTo(BigInteger.ZERO) > 0; 
           tmp = tmp.divide(new BigInteger("2"))) {
        bit++;
      }

      num = new BigInteger(bit, rnd);
      num = num.remainder(max);
      return num;
    }
    
    /* 16進数変換 */
    private static String asHex(String num) {
        String target = num;

        target = Long.toString(Integer.valueOf(target).intValue(), 16);
        
        while (target.length() < 2) {
            target = "0" + target;
        }

        
        return target.toString().toUpperCase();
    }
}
