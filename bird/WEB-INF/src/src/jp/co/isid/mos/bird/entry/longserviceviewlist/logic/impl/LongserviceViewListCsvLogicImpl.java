/*
 * 作成日: 2006/12/27
 */
package jp.co.isid.mos.bird.entry.longserviceviewlist.logic.impl;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.seasar.framework.container.S2Container;
import org.seasar.framework.container.factory.SingletonS2ContainerFactory;

import jp.co.isid.mos.bird.entry.common.code.ConditionKbn;
import jp.co.isid.mos.bird.entry.longserviceviewlist.dao.UILSViewListCsvDao;
import jp.co.isid.mos.bird.entry.longserviceviewlist.dto.LongserviceViewListRequestDto;
import jp.co.isid.mos.bird.entry.longserviceviewlist.entity.UILSViewListCSV;
import jp.co.isid.mos.bird.entry.longserviceviewlist.entity.UILSViewListEvent;
import jp.co.isid.mos.bird.entry.longserviceviewlist.util.LongserviceViewListUtil;
import jp.co.isid.mos.bird.entry.nationalentry.util.NationalEntryUtil;
import jp.co.isid.mos.bird.framework.control.BirdDateInfo;
import jp.co.isid.mos.bird.framework.dto.CsvOutputDto;
import jp.co.isid.mos.bird.framework.exception.NotExistException;
import jp.co.isid.mos.bird.framework.logic.CsvOutputLogic;
import jp.co.isid.mos.bird.framework.util.formatter.DateFormatter;

/**
 * 永年勤続申請状況確認 CSVロジック
 *
 * @author xamaruyama
 * 更新日：2012/12/04 xkinu「本部申込開始日」と「ｸﾛｰｽﾞ日」を比較、
 *                     本部申込開始日より前にクローズした店舗は、画面に表示させないようにする。
 * 更新日：2009/08/11 支部制限時のユーザーIDがパラメータに存在せず、
 *                    SQLでuserIdのパラメータありきで設定されているため、
 *                    常に支部制限のユーザは該当データ無しになっていました。
 *                    パラメータuserIdを追加し、対応しました。
 */
public class LongserviceViewListCsvLogicImpl implements CsvOutputLogic {

    /** ロジックID */
    public static final String LOGIC_ID = LongserviceViewListUtil.SCREEN_ID+"L03";

    /** CSVダウンロード 申請選択時 */
    private static final String KBN_APPLY = "1";

    /** CSVダウンロード 未申請選択時 */
//    private static final String KBN_NOAPPLY = "3";

    /** Dao【オーナー別申請状況一覧CSV】 */
    private UILSViewListCsvDao uILSViewListCsvDao;

    /**
     * CSV出力処理
     *
	 * 更新日：2012/12/04 xkinu「本部申込開始日」と「ｸﾛｰｽﾞ日」を比較、
	 *                     本部申込開始日より前にクローズした店舗は、画面に表示させないようにする。
	 * 更新日：2009/08/11 支部制限時のユーザーIDがパラメータに存在せず、
	 *                    SQLでuserIdのパラメータありきで設定されているため、
	 *                    常に支部制限のユーザは該当データ無しになっていました。
	 *                    パラメータuserIdを追加し、対応しました。
     */
    public List getOutputData(CsvOutputDto csvOutputDto) {

        List listCSV = new ArrayList();
        DateFormatter dateFormatter  = new DateFormatter();
        //リクエスト用DTO
        LongserviceViewListRequestDto reqDto = (LongserviceViewListRequestDto) csvOutputDto;

        String userId = reqDto.getUserId();
        String sysDate = reqDto.getSysdate();
        UILSViewListEvent entity = reqDto.getEntityUILSViewListEvent();
        String entryCd = entity.getEntryCd();
        String entryYear = entity.getEntryYear();
        String entryKai = entity.getEntryKai();
        String companyCd = reqDto.getCompanyCd();
        boolean limit = reqDto.isLimit();
        //区分取得
        String KbnChoice = reqDto.getKbnChoice();
        //検索処理(SQL出力)
        List csv = null;
        //区分が申請の場合。
        if(ConditionKbn.VALUE_ENTRY.equals(KbnChoice)){
            csv = getUILSViewListCsvDao().getOnerApply(userId, limit
                    , entryCd, entryYear, entryKai, sysDate, companyCd, KbnChoice, entity.getHonbuFromDt());
        }
        else{
            csv = getUILSViewListCsvDao().getOnerNoApply(userId, limit
                    , entryCd, entryYear, entryKai, sysDate, companyCd, KbnChoice, entity.getHonbuFromDt());
        }
        //該当データが存在しない場合、下記の処理を行う。
        if (csv == null || csv.size() == 0) {
            throw new NotExistException("該当データ");
        }

        // CSV出力用List
        // ヘッダ部
        makeEvent((LongserviceViewListRequestDto) csvOutputDto, listCSV);

        for (Iterator ite = csv.iterator(); ite.hasNext();) {
            //Listからentityへキャストする
            UILSViewListCSV e1Row = (UILSViewListCSV) ite.next();
            //１行分のリスト作成
            List listData = new ArrayList();

            //オーナーコード
            String onerCd = e1Row.getOnerCd();
            listData.add(onerCd==null?"":onerCd.trim());
            //オーナー名称
            String onerNameKj = e1Row.getOnerNameKj();
            listData.add(onerNameKj==null?"":onerNameKj.trim());

            //ソート番号
            String seqNo = e1Row.getSeqNo();
            listData.add(seqNo==null?"":seqNo.trim());

            //支部コード
            String sibuCd = e1Row.getSibuCd();
            listData.add(sibuCd==null?"":sibuCd.trim());
            //支部名称
            String sibuName = e1Row.getSibuName();
            listData.add(sibuName==null?"":sibuName.trim());

            //店舗コード
            String miseCd = e1Row.getMiseCd();
            listData.add(miseCd == null?"":miseCd.trim());

            //店舗名称
            String miseNameKj = e1Row.getMiseNameKj();
            listData.add(miseNameKj == null?"":miseNameKj.trim());

            //責任者氏名
            String name = e1Row.getName();
            listData.add(name == null?"":name.trim());

            //電話番号
            String tel = e1Row.getTel();
            listData.add(tel == null?"":tel.trim());

            //氏名漢字(姓)
            String seiKj = e1Row.getLNameKj();
            listData.add(seiKj == null?"":seiKj.trim());

            //氏名漢字(名)
            String nameKj = e1Row.getFNameKj();
            listData.add(nameKj == null?"":nameKj.trim());

            //氏名ローマ字(姓)
            String seiRm = e1Row.getLNameRm();
            listData.add(seiRm == null?"":seiRm.trim());

            //氏名ローマ字(名)
            String nameRm = e1Row.getFNameRm();
            listData.add(nameRm == null?"":nameRm.trim());

            //性別
            String sex = e1Row.getSex();
            listData.add(sex == null?"":sex.trim());

            //生年月日
            String birthday = e1Row.getBirthday();
            listData.add(birthday == null?"":dateFormatter.format(birthday
					,DateFormatter.PATTERN_NORMAL
					,DateFormatter.PATTERN_SLASH
					,false));
            //年齢
//            String age = e1Row.getAge());
            String age = getNewAge(birthday);
            listData.add(age == null?"":age.trim());

            //入社年月日
            String entryDate = e1Row.getEntryDate();
            listData.add(entryDate == null?"":dateFormatter.format(entryDate
					,DateFormatter.PATTERN_NORMAL
					,DateFormatter.PATTERN_SLASH
					,false));
//            //年号(昭和(ERA_SHOUWA):3 平成(ERA_HEISEI):4 CSVには、昭和なら"0"、平成なら"1"を出力します。)
//            String lastUser = e1Row.getLastUser();
//            String era = DateUtils.getEra(e1Row.getEntryDate());
//            listData.add(lastUser == null?"":(DateUtils.ERA_SYOUWA.equals(era)?"0":"1"));
//
//            //入社年
//            listData.add(lastUser == null?"":DateUtils.getEraYear(e1Row.getEntryDate()));
//
//            //入社月
//            listData.add(lastUser == null?"":DateUtils.getMonth(e1Row.getEntryDate()));
//
//            //入社日
//            listData.add(lastUser == null?"":DateUtils.getDay(e1Row.getEntryDate()));

            //店舗経験
            String expKbn = e1Row.getExpKbn();
            listData.add(expKbn == null?"":expKbn.trim());

            //更新者ID
            String lastUser = e1Row.getLastUser();
            listData.add(lastUser == null?"":lastUser.trim());

            //更新者名称
            String userNameKj = e1Row.getUserNameKj();
            listData.add(userNameKj == null?"":userNameKj.trim());

            //オーナー契約終了日
            String keiyakuEnd = e1Row.getKeiyakuEnd();
            listData.add(keiyakuEnd == null?"":dateFormatter.format(keiyakuEnd
            									,DateFormatter.PATTERN_NORMAL
            									,DateFormatter.PATTERN_SLASH
            									,false));

            listCSV.add(listData);

        }// end of for

        return listCSV;
    }

/**
 * データ部
 *
 * @param dto
 * @param listCsv
 */
    private void makeEvent(LongserviceViewListRequestDto dto, List listCsv) {

        List Event1 = new ArrayList();
        List Event2 = new ArrayList();
        List Event3 = new ArrayList();
        List Event4 = new ArrayList();
        List Event5 = new ArrayList();
        DateFormatter dateFormatter  = new DateFormatter();
        UILSViewListEvent uILSViewListEvent = dto.getEntityUILSViewListEvent();

        // 一行目（開催日、本部申込開始日、オーナー申込開始日）
        Event1.add("イベント名称：");
        Event1.add(uILSViewListEvent.getEntryTitle().trim());
        Event1.add("");
        Event1.add("本部申込開始日：");
        Event1.add(dateFormatter.format(uILSViewListEvent.getHonbuFromDt(),DateFormatter.PATTERN_NORMAL,DateFormatter.PATTERN_SLASH,false));
        Event1.add("");
        Event1.add("オーナー申込開始日：");
        Event1.add(dateFormatter.format(uILSViewListEvent.getOnerFromDt(),DateFormatter.PATTERN_NORMAL,DateFormatter.PATTERN_SLASH,false));

        // ニ行目（説明会名称、本部申込終了日、オーナー申込終了日）
        Event2.add("区分：");
        if (KBN_APPLY.equals(dto.getKbnChoice())) {
            Event2.add("申請");
        } else {
            Event2.add("未申請");
        }
        Event2.add("");
        Event2.add("本部申込終了日：");
        Event2.add(dateFormatter.format(uILSViewListEvent.getHonbuToDt(),DateFormatter.PATTERN_NORMAL,DateFormatter.PATTERN_SLASH,false));
        Event2.add("");
        Event2.add("オーナー申込終了日：");
        Event2.add(dateFormatter.format(uILSViewListEvent.getOnerToDt(),DateFormatter.PATTERN_NORMAL,DateFormatter.PATTERN_SLASH,false));

        //三行目（注意書き）
        Event3.add("※支部コード、支部名称はオーナーマスタに登録されている支部が表示されます。");
        Event3.add("");
        Event3.add("");

        //四行目（空欄）
        Event4.add("");

        //五行目（データのヘッダ）
        Event5.add("オーナーコード");
        Event5.add("オーナー名称");
        Event5.add("SEQ_NO");
        Event5.add("支部コード");
        Event5.add("支部名称");
        Event5.add("店舗コード");
        Event5.add("店舗名称");
        Event5.add("申込責任者");
        Event5.add("申込者電話番号");
        Event5.add("参加者氏（漢字）");
        Event5.add("参加者名（漢字）");
        Event5.add("参加者氏（ローマ字）");
        Event5.add("参加者名（ローマ字）");
        Event5.add("性別");
        Event5.add("生年月日");
        Event5.add("年齢");
        Event5.add("入社年月日");
//        Event5.add("年号");
//        Event5.add("入社年");
//        Event5.add("入社月");
//        Event5.add("入社日");
        Event5.add("店舗経験");
        Event5.add("更新者ID");
        Event5.add("更新者名称");
        Event5.add("オーナー契約終了日");

        listCsv.add(Event1);
        listCsv.add(Event2);
        listCsv.add(Event3);
        listCsv.add(Event4);
        listCsv.add(Event5);

    }

    public String getFileName(CsvOutputDto csvOutputDto) {

//      リクエスト用DTO
        LongserviceViewListRequestDto reqDto = (LongserviceViewListRequestDto) csvOutputDto;
        UILSViewListEvent entity = reqDto.getEntityUILSViewListEvent();
        String entryYear = entity.getEntryYear();
        String entryKai = entity.getEntryKai();
        String kbnChoice = reqDto.getKbnChoice();
        String fileName = "";
        if(kbnChoice.equals(ConditionKbn.VALUE_ENTRY)){
            //申請(1)
            fileName = "LONGTIME_SHINSEI" + entryYear + entryKai + ".csv";
        } else if (kbnChoice.equals(ConditionKbn.VALUE_MITOUROKU)) {
            //未申請(3)
            fileName = "LONGTIME_MISHINSEI" + entryYear + entryKai + ".csv";
        }
        return fileName;

    }

    public void validate(CsvOutputDto csvOutputDto) {
        // TODO 自動生成されたメソッド・スタブ
    }

    public UILSViewListCsvDao getUILSViewListCsvDao() {
        return uILSViewListCsvDao;
    }

    public void setUILSViewListCsvDao(UILSViewListCsvDao viewListCsvDao) {
        uILSViewListCsvDao = viewListCsvDao;
    }

    /**
     * 年齢再計算する
     * @param birthday
     * @return
     */
    private String getNewAge(String birthday){
    	 // コンポーネント取得
        S2Container container = SingletonS2ContainerFactory.getContainer();
        //日付情報を取得
        BirdDateInfo birdDateInfo = (BirdDateInfo) container.getComponent(BirdDateInfo.class);
        String sysDate = birdDateInfo.getSysDate();
        String age = null;
        age = String.valueOf(NationalEntryUtil.chgToAgeFrDate(birthday,sysDate));
        if ("0".equals(age)) {
        	age = "";
        }
        return age;
    }

}