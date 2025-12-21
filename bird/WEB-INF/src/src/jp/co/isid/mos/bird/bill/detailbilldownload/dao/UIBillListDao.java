package jp.co.isid.mos.bird.bill.detailbilldownload.dao;
import java.util.List;
import jp.co.isid.mos.bird.bill.detailbilldownload.entity.UIBillList;


/**
 * 請求書一覧情報
 * @author xwatanabe
 */
public interface UIBillListDao {

    public static final Class BEAN = UIBillList.class;

    public static final String getBillList_ARGS = "companyCd, ownerCd, urikakeCd, startYM, endYM";
    
    /**
     * 請求書一覧の取得
     * @param  会社コード
     * @param  オーナコード
     * @param  売掛先コード
     * @param  表示開始年月日
     * @param  表示終了年月日
     * @return List
     */
    public List getBillList(String companyCd, String ownerCd, String urikakeCd, String startYM, String endYm);
    
}