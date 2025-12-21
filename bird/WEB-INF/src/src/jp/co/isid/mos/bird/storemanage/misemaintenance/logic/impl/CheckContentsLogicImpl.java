package jp.co.isid.mos.bird.storemanage.misemaintenance.logic.impl;

import java.math.BigDecimal;
import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;

import jp.co.isid.mos.bird.framework.exception.ApplicationException;
import jp.co.isid.mos.bird.framework.exception.DuplicateDataException;
import jp.co.isid.mos.bird.framework.exception.GenericErrorException;
import jp.co.isid.mos.bird.framework.exception.InvalidInputException;
import jp.co.isid.mos.bird.framework.exception.NoInputException;
import jp.co.isid.mos.bird.framework.exception.NotNullException;
import jp.co.isid.mos.bird.framework.util.verifier.CodeVerifier;
import jp.co.isid.mos.bird.framework.util.verifier.DateVerifier;
import jp.co.isid.mos.bird.framework.util.verifier.LengthVerifier;
import jp.co.isid.mos.bird.framework.util.verifier.NumericVerifier;
import jp.co.isid.mos.bird.framework.util.verifier.PhoneVerifier;
import jp.co.isid.mos.bird.storemanage.misemaintenance.dto.MiseMaintenanceDto;
import jp.co.isid.mos.bird.storemanage.misemaintenance.entity.MstBukken;
import jp.co.isid.mos.bird.storemanage.misemaintenance.entity.MstChintai;
import jp.co.isid.mos.bird.storemanage.misemaintenance.entity.MstEventStatus;
import jp.co.isid.mos.bird.storemanage.misemaintenance.entity.MstMiseKaiso;
import jp.co.isid.mos.bird.storemanage.misemaintenance.entity.MstSB;
import jp.co.isid.mos.bird.storemanage.misemaintenance.entity.MstTO;
import jp.co.isid.mos.bird.storemanage.misemaintenance.logic.CheckContentsLogic;

/**
 * 登録内容のチェック ロジック
 * 更新：2008/06/09 サービス実施状況タブの入力チェック変更 （PK-011050-005）
 * @author xnkusama
 */
public class CheckContentsLogicImpl implements CheckContentsLogic {

    /* ロジックID */
    public static final String LOGIC_ID = "BSM001L03";

    private static final String MOS_COMPANY_CD = "00";

    /* イベント分類：宅配 */
    private static final String EVENT_BNRUI_TAKUHAI = "02";

    /**
     * 登録内容のチェックを行う
     * @param MiseMaintenanceDto 画面DTO
     * @exception ApplicationException
     */
    public void execute(MiseMaintenanceDto dto) throws ApplicationException {
        //TODO 2006/02/21 チェックを実装

        String companyCd = dto.getCondCompanyCd();

//        if (MOS_COMPANY_CD.equals(companyCd)) {
//            chkKinkyuRenraku(dto);
//        }
//        else {
//            //20060524追加
//            removeEmptyData(dto);
//            checkOther(dto);
//        }

        if (!MOS_COMPANY_CD.equals(companyCd)) {
            //20060524追加
            removeEmptyData(dto);
            checkOther(dto);
        }
        checkCommon(dto);
    }


    /**
     * 緊急連絡先 チェック
     * @param dto
     * @throws ApplicationException
     */
    private void chkKinkyuRenraku(MiseMaintenanceDto dto) throws ApplicationException {
        PhoneVerifier phoneVerifier = new PhoneVerifier(15, true);
        LengthVerifier lengthVerifier40 = new LengthVerifier(40);

        //20060517 追加修正

        /** 特性・営業時間 -------------------------------------------------------*/
        //タブ表示設定　******
        dto.setSelectedTab(2);

        // 緊急連絡先1
        String emergenceTel1 = dto.getMstMiseYobi().getEmergenceTel1();
        if (!phoneVerifier.validate(emergenceTel1)) {
            throw new InvalidInputException("緊急連絡先：緊急連絡先1");
        }

        // 緊急連絡先2
        String emergenceTel2 = dto.getMstMiseYobi().getEmergenceTel2();
        if (!phoneVerifier.validate(emergenceTel2)) {
            throw new InvalidInputException("緊急連絡先：緊急連絡先2");
        }

        // 緊急連絡先3
        String emergenceTel3 = dto.getMstMiseYobi().getEmergenceTel3();
        if (!phoneVerifier.validate(emergenceTel3)) {
            throw new InvalidInputException("緊急連絡先：緊急連絡先3");
        }


        // 緊急連絡先名1
        String emergenceName1 = dto.getMstMiseYobi().getEmergenceName1();
        if (!lengthVerifier40.validate(emergenceName1)) {
            throw new InvalidInputException("緊急連絡先：緊急連絡先名1");
        }

        // 緊急連絡先名2
        String emergenceName2 = dto.getMstMiseYobi().getEmergenceName2();
        if (!lengthVerifier40.validate(emergenceName2)) {
            throw new InvalidInputException("緊急連絡先：緊急連絡先名2");
        }

        // 緊急連絡先名1
        String emergenceName3 = dto.getMstMiseYobi().getEmergenceName3();
        if (!lengthVerifier40.validate(emergenceName3)) {
            throw new InvalidInputException("緊急連絡先：緊急連絡先名3");
        }

    }

    // モス以外チェック
    private void checkOther(MiseMaintenanceDto dto) throws ApplicationException {
        NumericVerifier numericVerifier    = new NumericVerifier(false, 4);
        NumericVerifier numericVerifier5_2 = new NumericVerifier(false, 5, 2);
        LengthVerifier lengthVerifier20    = new LengthVerifier(20);
        LengthVerifier lengthVerifier40    = new LengthVerifier(40);
        LengthVerifier lengthVerifier200   = new LengthVerifier(200);
        LengthVerifier lengthVerifier1     = new LengthVerifier(1);
        LengthVerifier lengthVerifier5     = new LengthVerifier(5);
        LengthVerifier lengthVerifier400   = new LengthVerifier(400);
        DateVerifier dateVerifier          = new DateVerifier(DateVerifier.DATE_TYPE_YMD);
        CodeVerifier codeVerifierOner      = new CodeVerifier(5, false);
        CodeVerifier codeVerifierMise      = new CodeVerifier(5, false);
        CodeVerifier codeVerifierPark      = new CodeVerifier(5, false);
        CodeVerifier codeVerifierSeki      = new CodeVerifier(5, false);
        CodeVerifier codeVerifier5         = new CodeVerifier(5, false);
        CodeVerifier codeVerifier11        = new CodeVerifier(11, false);

        /** 特性・営業時間 -------------------------------------------------------*/
        //タブ表示設定　******
        dto.setSelectedTab(2);

        //平日 開始時間
        String openTmWkd = dto.getMstMise().getOpenTmWkd();
        if (!numericVerifier.validate(openTmWkd)) {
            throw new InvalidInputException("営業時間：平日 開始時間");
        }
        //平日 終了時間
        String closeTmWkd = dto.getMstMise().getCloseTmWkd();
        if (!numericVerifier.validate(closeTmWkd)) {
            throw new InvalidInputException("営業時間：平日 終了時間");
        }

        //日曜日 開始時間
        String openTmHol = dto.getMstMise().getOpenTmHol();
        if (!numericVerifier.validate(openTmHol)) {
            throw new InvalidInputException("営業時間：日曜日 開始時間");
        }
        //日曜日 終了時間
        String closeTmHol = dto.getMstMise().getCloseTmHol();
        if (!numericVerifier.validate(closeTmHol)) {
            throw new InvalidInputException("営業時間：日曜日 終了時間");
        }

        //土曜日 開始時間
        String openTmSat = dto.getMstMise().getOpenTmSat();
        if (!numericVerifier.validate(openTmSat)) {
            throw new InvalidInputException("営業時間：土曜日 開始時間");
        }
        //土曜日 終了時間
        String closeTmSat = dto.getMstMise().getCloseTmSat();
        if (!numericVerifier.validate(closeTmSat)) {
            throw new InvalidInputException("営業時間：土曜日 終了時間");
        }

        //休前日 開始時間
        String openTmHlb = dto.getMstMise().getOpenTmHlb();
        if (!numericVerifier.validate(openTmHlb)) {
            throw new InvalidInputException("営業時間：休前日 開始時間");
        }
        //休前日 終了時間
        String closeTmHlb = dto.getMstMise().getCloseTmHlb();
        if (!numericVerifier.validate(closeTmHlb)) {
            throw new InvalidInputException("営業時間：休前日 終了時間");
        }

//20060517 追加 start -------------------------------------
        //備考（店休日）
        String holNotes = dto.getMstMise().getHolNotes();
        if (!lengthVerifier200.validate(holNotes)) {
            throw new InvalidInputException("営業時間：備考");
        }
//20060517 追加 end ---------------------------------------

        /** 地図・物件情報 -------------------------------------------------------*/
        //タブ表示設定　******
        dto.setSelectedTab(3);

        String parkOnlyIn = dto.getMstMise().getParkOnlyIn();
        if (!codeVerifierPark.validate(parkOnlyIn)) {
            throw new InvalidInputException("駐車場：専用(敷地内)");
        }

        String parkOnlyOut = dto.getMstMise().getParkOnlyOut();
        if (!codeVerifierPark.validate(parkOnlyOut)) {
            throw new InvalidInputException("駐車場：専用(隣接)");
        }

        String parkCommIn = dto.getMstMise().getParkCommIn();
        if (!codeVerifierPark.validate(parkCommIn)) {
            throw new InvalidInputException("駐車場：共用(敷地内)");
        }

        String parkCommOut = dto.getMstMise().getParkCommOut();
        if (!codeVerifierPark.validate(parkCommOut)) {
            throw new InvalidInputException("駐車場：共用(隣接)");
        }

        /* 駐車場:総台数 ＞ 99999 の場合、エラー */
        String parkKei = dto.getMstMise().getParkKei();
        if((new BigDecimal(parkKei)).compareTo(new BigDecimal("99999")) == 1){
            throw new GenericErrorException("駐車場：総台数が台数上限");
        }

        //投資金額
        NumericVerifier prjVerifier = new NumericVerifier(false, 11, 0);
        String prjInvest = dto.getMstMise().getPrjInvest();
        if (!prjVerifier.validate(prjInvest)) {
            throw new InvalidInputException("事業計画：投資金額", "prjInvest", 3);
        }
        //事業計画売上
        String prjUriage = dto.getMstMise().getPrjUriage();
        if (!prjVerifier.validate(prjUriage)) {
            throw new InvalidInputException("事業計画：事業計画売上", "prjUriage", 3);
        }

        //設計業者
        String toriSekkei = dto.getMstMise().getToriSekkei();
        if (!lengthVerifier40.validate(toriSekkei)) {
            throw new InvalidInputException("設計・施工：設計業者");
        }
        //施工業者
        String toriSekou = dto.getMstMise().getToriSekou();
        if (!lengthVerifier40.validate(toriSekou)) {
            throw new InvalidInputException("設計・施工：施工業者");
        }
        //最寄の駅（線）
        String accessLine = dto.getMstMise().getAccessLine();
        if (!lengthVerifier40.validate(accessLine)) {
            throw new InvalidInputException("交通手段：最寄の駅（線）");
        }
        //最寄の駅（名）
        String accessStation = dto.getMstMise().getAccessStation();
        if (!lengthVerifier40.validate(accessStation)) {
            throw new InvalidInputException("交通手段：最寄の駅（名）");
        }
        //交通手段

        //交通手段備考
        String accessWayNote = dto.getMstMise().getAccessWayNote();
        if (!lengthVerifier200.validate(accessWayNote)) {
            throw new InvalidInputException("交通手段：交通手段備考");
        }
        //最寄の駅からの距離
        BigDecimal accessDist = dto.getMstMise().getAccessDist();
        if (!numericVerifier5_2.validate(accessDist)) {
            throw new InvalidInputException("交通手段：最寄の駅からの距離");
        }
        //最寄の駅からの時間
        String accessTime = dto.getMstMise().getAccessTime();
        if (!codeVerifier5.validate(accessTime)) {
            throw new InvalidInputException("交通手段：最寄の駅からの時間");
        }

        /** 客席情報 -------------------------------------------------------------*/
        //タブ表示設定　******
        dto.setSelectedTab(4);

        String seat1fSmokingIn = dto.getMstMise().getSeat1FSmokingIn();
        if (!codeVerifierSeki.validate(seat1fSmokingIn)) {
            throw new InvalidInputException("客席情報：喫煙席数 １Ｆ店内");
        }
        String seat1fSmokingOut = dto.getMstMise().getSeat1FSmokingOut();
        if (!codeVerifierSeki.validate(seat1fSmokingOut)) {
            throw new InvalidInputException("客席情報：喫煙席数 １Ｆ店外");
        }
        String seat1fNonSmokingIn = dto.getMstMise().getSeat1FNonSmokingIn();
        if (!codeVerifierSeki.validate(seat1fNonSmokingIn)) {
            throw new InvalidInputException("客席情報：禁煙席数 １Ｆ店内");
        }
        String seat1fNonSmokingOut = dto.getMstMise().getSeat1FNonSmokingOut();
        if (!codeVerifierSeki.validate(seat1fNonSmokingOut)) {
            throw new InvalidInputException("客席情報：禁煙席数 １Ｆ店外");
        }
//        String seat1F = dto.getMstMise().getSeat1F();
//        if (!codeVerifierSeki.validate(seat1F)) {
//            throw new InvalidInputException("客席情報：１Ｆ フロア計");
//        }
        String seat2fSmokingIn = dto.getMstMise().getSeat2FSmokingIn();
        if (!codeVerifierSeki.validate(seat2fSmokingIn)) {
            throw new InvalidInputException("客席情報：喫煙席数 ２Ｆ店内");
        }
        String seat2fSmokingOut = dto.getMstMise().getSeat2FSmokingOut();
        if (!codeVerifierSeki.validate(seat2fSmokingOut)) {
            throw new InvalidInputException("客席情報：喫煙席数 ２Ｆ店外");
        }
        String seat2fNonSmokingIn = dto.getMstMise().getSeat2FNonSmokingIn();
        if (!codeVerifierSeki.validate(seat2fNonSmokingIn)) {
            throw new InvalidInputException("客席情報：禁煙席数 ２Ｆ店内");
        }
        String seat2fNonSmokingOut = dto.getMstMise().getSeat2FNonSmokingOut();
        if (!codeVerifierSeki.validate(seat2fNonSmokingOut)) {
            throw new InvalidInputException("客席情報：禁煙席数 ２Ｆ店外");
        }
//        String seat2F = dto.getMstMise().getSeat2F();
//        if (!codeVerifierSeki.validate(seat2F)) {
//            throw new InvalidInputException("客席情報：２Ｆ フロア計");
//        }
        String seat3fSmokingIn = dto.getMstMise().getSeat3FSmokingIn();
        if (!codeVerifierSeki.validate(seat3fSmokingIn)) {
            throw new InvalidInputException("客席情報：喫煙席数 ３Ｆ店内");
        }
        String seat3fSmokingOut = dto.getMstMise().getSeat3FSmokingOut();
        if (!codeVerifierSeki.validate(seat3fSmokingOut)) {
            throw new InvalidInputException("客席情報：喫煙席数 ３Ｆ店外");
        }
        String seat3fNonSmokingIn = dto.getMstMise().getSeat3FNonSmokingIn();
        if (!codeVerifierSeki.validate(seat3fNonSmokingIn)) {
            throw new InvalidInputException("客席情報：禁煙席数 ３Ｆ店内");
        }
        String seat3fNonSmokingOut = dto.getMstMise().getSeat3FNonSmokingOut();
        if (!codeVerifierSeki.validate(seat3fNonSmokingOut)) {
            throw new InvalidInputException("客席情報：禁煙席数 ３Ｆ店外");
        }
//        String seat3F = dto.getMstMise().getSeat3F();
//        if (!codeVerifierSeki.validate(seat3F)) {
//            throw new InvalidInputException("客席情報：３Ｆ フロア計");
//        }
        String seatUnderSmokingIn = dto.getMstMise().getSeatUnderSmokingIn();
        if (!codeVerifierSeki.validate(seatUnderSmokingIn)) {
            throw new InvalidInputException("客席情報：喫煙席数 地下店内");
        }
        String seatUnderSmokingOut = dto.getMstMise().getSeatUnderSmokingOut();
        if (!codeVerifierSeki.validate(seatUnderSmokingOut)) {
            throw new InvalidInputException("客席情報：喫煙席数 地下店外");
        }
        String seatUnderNonSmokingIn = dto.getMstMise().getSeatUnderNonSmokingIn();
        if (!codeVerifierSeki.validate(seatUnderNonSmokingIn)) {
            throw new InvalidInputException("客席情報：禁煙席数 地下店内");
        }
        String seatUnderNonSmokingOut = dto.getMstMise().getSeatUnderNonSmokingOut();
        if (!codeVerifierSeki.validate(seatUnderNonSmokingOut)) {
            throw new InvalidInputException("客席情報：禁煙席数 地下店外");
        }
//        String seatUnder = dto.getMstMise().getSeatUnder();
//        if (!codeVerifierSeki.validate(seatUnder)) {
//            throw new InvalidInputException("客席情報：地下 フロア計");
//        }
        String seatCommonSmokingIn = dto.getMstMise().getSeatCommonSmokingIn();
        if (!codeVerifierSeki.validate(seatCommonSmokingIn)) {
            throw new InvalidInputException("客席情報：喫煙席数 共用店内");
        }
        String seatCommonSmokingOut = dto.getMstMise().getSeatCommonSmokingOut();
        if (!codeVerifierSeki.validate(seatCommonSmokingOut)) {
            throw new InvalidInputException("客席情報：喫煙席数 共用店外");
        }
        String seatCommonNonSmokingIn = dto.getMstMise().getSeatCommonNonSmokingIn();
        if (!codeVerifierSeki.validate(seatCommonNonSmokingIn)) {
            throw new InvalidInputException("客席情報：禁煙席数 共用店内");
        }
        String seatCommonNonSmokingOut = dto.getMstMise().getSeatCommonNonSmokingOut();
        if (!codeVerifierSeki.validate(seatCommonNonSmokingOut)) {
            throw new InvalidInputException("客席情報：禁煙席数 共用店外");
        }
//        String seatCommon = dto.getMstMise().getSeatCommon();
//        if (!codeVerifierSeki.validate(seatCommon)) {
//            throw new InvalidInputException("客席情報：共用 フロア計");
//        }
        String seatOtherSmokingIn = dto.getMstMise().getSeatOtherSmokingIn();
        if (!codeVerifierSeki.validate(seatOtherSmokingIn)) {
            throw new InvalidInputException("客席情報：喫煙席数 その他店内");
        }
        String seatOtherSmokingOut = dto.getMstMise().getSeatOtherSmokingOut();
        if (!codeVerifierSeki.validate(seatOtherSmokingOut)) {
            throw new InvalidInputException("客席情報：喫煙席数 その他店外");
        }
        String seatOtherNonSmokingIn = dto.getMstMise().getSeatOtherNonSmokingIn();
        if (!codeVerifierSeki.validate(seatOtherNonSmokingIn)) {
            throw new InvalidInputException("客席情報：禁煙席数 その他店内");
        }
        String seatOtherNonSmokingOut = dto.getMstMise().getSeatOtherNonSmokingOut();
        if (!codeVerifierSeki.validate(seatOtherNonSmokingOut)) {
            throw new InvalidInputException("客席情報：禁煙席数 その他店外");
        }
//        String seatOther = dto.getMstMise().getSeatOther();
//        if (!codeVerifierSeki.validate(seatOther)) {
//            throw new InvalidInputException("客席情報：その他 フロア計");
//        }

//      20060525 追加 start ---------------------------------------------
//      /* 総席数 ＞ 32767 の場合、エラー */
//      BigDecimal tSouSekisuu = dto.getMstMise().getTSouSekisuu();
//      if((tSouSekisuu).compareTo(new BigDecimal("32767")) == 1){
//          throw new GenericErrorException("客席情報：総席数が客席数上限");
//      }

      /* 総席数 ＞ 99999 の場合、エラー */
      String tSouSekisuu = dto.getMstMise().getTSouSekisuu();
      if((new BigDecimal(tSouSekisuu)).compareTo(new BigDecimal("99999")) == 1){
          throw new GenericErrorException("客席情報：総席数が客席数上限");
      }

      /** 1F席有が選択されているのに、フロア計が0席の場合、エラー */
      BigDecimal seat1F = new BigDecimal(dto.getMstMise().getSeat1F());
      if (dto.getMstMise().getSeat1fFlgBoolean()) {
          if (seat1F.compareTo(new BigDecimal("0")) < 1) {
              throw new NoInputException("客席情報：1F席数");
          }
      }
      else{
          if (seat1F.compareTo(new BigDecimal("0")) == 1) {
              dto.getMstMise().setSeat1fFlgBoolean(true);
          }
      }

      /** 2F席有が選択されているのに、フロア計が0席の場合、エラー */
      BigDecimal seat2F = new BigDecimal(dto.getMstMise().getSeat2F());
      if (dto.getMstMise().getSeat2fFlgBoolean()) {
          if (seat2F.compareTo(new BigDecimal("0")) < 1) {
              throw new NoInputException("客席情報：2F席数");
          }
      }
      else{
          if (seat2F.compareTo(new BigDecimal("0")) == 1) {
              dto.getMstMise().setSeat2fFlgBoolean(true);
          }
      }

      /** 3F席有が選択されているのに、フロア計が0席の場合、エラー */
      BigDecimal seat3F = new BigDecimal(dto.getMstMise().getSeat3F());
      if (dto.getMstMise().getSeat3fFlgBoolean()) {
          if (seat3F.compareTo(new BigDecimal("0")) < 1) {
              throw new NoInputException("客席情報：3F席数");
          }
      }
      else{
          if (seat3F.compareTo(new BigDecimal("0")) == 1) {
              dto.getMstMise().setSeat3fFlgBoolean(true);
          }
      }

      /** 地下席有が選択されているのに、フロア計が0席の場合、エラー */
      BigDecimal seatUnder = new BigDecimal(dto.getMstMise().getSeatUnder());
      if (dto.getMstMise().getSeatUnderFlgBoolean()) {
          if (seatUnder.compareTo(new BigDecimal("0")) < 1) {
              throw new NoInputException("客席情報：地下席数");
          }
      }
      else{
          if (seatUnder.compareTo(new BigDecimal("0")) == 1) {
              dto.getMstMise().setSeatUnderFlgBoolean(true);
          }
      }

      /** 共有席有が選択されているのに、フロア計が0席の場合、エラー */
      BigDecimal seatCommon = new BigDecimal(dto.getMstMise().getSeatCommon());
      if (dto.getMstMise().getSeatCommonFlgBoolean()) {
          if (seatCommon.compareTo(new BigDecimal("0")) < 1) {
              throw new NoInputException("客席情報：共有席数");
          }
      }
      else{
          if (seatCommon.compareTo(new BigDecimal("0")) == 1) {
              dto.getMstMise().setSeatCommonFlgBoolean(true);
          }
      }

      /** その他席有が選択されているのに、フロア計が0席の場合、エラー */
      BigDecimal seatOther = new BigDecimal(dto.getMstMise().getSeatOther());
      if (dto.getMstMise().getSeatOtherFlgBoolean()) {
          if (seatOther.compareTo(new BigDecimal("0")) < 1) {
              throw new NoInputException("客席情報：その他席数");
          }
      }
      else{
          if (seatOther.compareTo(new BigDecimal("0")) == 1) {
              dto.getMstMise().setSeatOtherFlgBoolean(true);
          }
      }
//20060525 追加 end -----------------------------------------------

        /** 店舗契約情報 ---------------------------------------------------------*/
        //タブ表示設定　******
        dto.setSelectedTab(5);

        List listBukken = dto.getListBukken();
        for (Iterator ite = listBukken.iterator(); ite.hasNext();) {
        	MstBukken entity = (MstBukken) ite.next();
        	// 更新日
        	String keiyakuUpdate = entity.getKeiyakuUpdate();
        	if (isNull(keiyakuUpdate)) {
        		throw new NotNullException("物件情報：更新日");
        	}
        	if (!dateVerifier.validate(keiyakuUpdate)) {
        		throw new InvalidInputException("物件情報：更新日");
        	}

        	// 家賃
        	String keiyakuYachin = entity.getKeiyakuYachin();
        	if (!codeVerifier11.validate(keiyakuYachin)) {
        		throw new InvalidInputException("物件情報：家賃");
        	}
        	// 家主
        	String keiyakuOner = entity.getKeiyakuOner();
        	if (!lengthVerifier40.validate(keiyakuOner)) {
        		throw new InvalidInputException("物件情報：家主");
        	}
        }
    	// 物件情報 重複チェック
    	Comparator sortComparatorBukken = new Comparator() {
            public boolean equals(Object obj) {
                return (super.equals(obj));
            }
            public int compare(Object obj1, Object obj2) {
            	String key1 = ((MstBukken) obj1).getKeiyakuUpdate();
            	String key2 = ((MstBukken) obj2).getKeiyakuUpdate();
                return key1.compareTo(key2);
            }
    	};
    	Collections.sort(listBukken, sortComparatorBukken);
    	String oldKeyBukken = "";
    	String newKeyBukken = "";
    	for (Iterator ite = listBukken.iterator(); ite.hasNext();) {
    		MstBukken entity = (MstBukken) ite.next();
    		newKeyBukken = entity.getKeiyakuUpdate();
    		if (oldKeyBukken.equals(newKeyBukken)) {
    			throw new DuplicateDataException("物件情報");
    		}
    		oldKeyBukken = newKeyBukken;
    	}
        // 家賃備考
        String keiyakuNotes = dto.getMstMise().getKeiyakuNotes();
    	if (!lengthVerifier200.validate(keiyakuNotes)) {
    		throw new InvalidInputException("家賃備考");
    	}
        // 転貸
        String tentai = dto.getMstMise().getTentai();
    	if (!lengthVerifier1.validate(tentai)) {
    		throw new InvalidInputException("転貸");
    	}
        // 転貸開始日
        String tentaiStartDt = dto.getMstMise().getTentaiStartDt();
    	if (!dateVerifier.validate(tentaiStartDt)) {
    		throw new InvalidInputException("転貸開始日");
    	}
        // 転貸終了日
        String tentaiEndDt = dto.getMstMise().getTentaiEndDt();
    	if (!dateVerifier.validate(tentaiEndDt)) {
    		throw new InvalidInputException("転貸終了日");
    	}
        // 転貸情報
        String tentaiInfo = dto.getMstMise().getTentaiInfo();
        if (!lengthVerifier400.validate(tentaiInfo)) {
    		throw new InvalidInputException("転貸情報");
    	}

        /* 賃貸情報 */
        List listChintai = dto.getListChintai();
        for (Iterator ite = listChintai.iterator(); ite.hasNext();) {
        	MstChintai entity = (MstChintai) ite.next();
            //賃貸店舗 20060524追加 start -----------------------
            String miseLeaseShu = entity.getMiseLeaseShu();
            if (isNull(miseLeaseShu)) {
                throw new NotNullException("賃貸情報：賃貸店舗");
            }
            // 20060524追加 end ---------------------------------
        	//賃貸店舗契約日
        	String miseLeaseStart = entity.getMiseLeaseStart();
        	if (isNull(miseLeaseStart)) {
        		throw new NotNullException("賃貸情報：賃貸店舗契約日");
        	}
        	if (!dateVerifier.validate(miseLeaseStart)) {
        		throw new InvalidInputException("賃貸情報：賃貸店舗契約日");
        	}
        	//契約終了予定日
        	String miseLeaseEnd = entity.getMiseLeaseEnd();
        	if (!dateVerifier.validate(miseLeaseEnd)) {
        		throw new InvalidInputException("賃貸情報：契約終了予定日");
        	}
        }
    	// 賃貸店舗種別重複チェック
    	Comparator sortComparatorChintai = new Comparator() {
            public boolean equals(Object obj) {
                return (super.equals(obj));
            }
            public int compare(Object obj1, Object obj2) {
            	String key1 = ((MstChintai) obj1).getMiseLeaseShu() + ((MstChintai) obj1).getMiseLeaseStart();
            	String key2 = ((MstChintai) obj2).getMiseLeaseShu() + ((MstChintai) obj2).getMiseLeaseStart();
                return key1.compareTo(key2);
            }
    	};
    	Collections.sort(listChintai, sortComparatorChintai);
    	String oldKeyChintai = "";
    	String newKeyChintai = "";
    	for (Iterator ite = listChintai.iterator(); ite.hasNext();) {
    		MstChintai entity = (MstChintai) ite.next();
    		newKeyChintai = entity.getMiseLeaseShu() + entity.getMiseLeaseStart();
    		if (oldKeyChintai.equals(newKeyChintai)) {
    			throw new DuplicateDataException("賃貸情報");
    		}
    		oldKeyChintai = newKeyChintai;
    	}



        /** 改装・T/O・S/B ---------------------------------------------------------*/
        //タブ表示設定　******
        dto.setSelectedTab(6);

        /* 改装 TO SB */
        List listKaiso = dto.getListMiseKaiso();
        for (Iterator ite = listKaiso.iterator(); ite.hasNext();) {
        	MstMiseKaiso entity = (MstMiseKaiso) ite.next();
        	// 改装区分
//        	BigDecimal kaisoKaisu = entity.getKaisoKaisu();
            String kaisoHoshu = entity.getKaisoHoshu();
        	if (kaisoHoshu == null) {
        		throw new NotNullException("店改装：改装区分");
        	}
        	if (!lengthVerifier20.validate(kaisoHoshu)) {
        		throw new InvalidInputException("店改装：改装区分");
        	}
        	// 開始日
        	String kaisoStartDt = entity.getKaisoSta();
        	if (!dateVerifier.validate(kaisoStartDt)) {
        		throw new InvalidInputException("店改装：開始日");
        	}
        	// 終了日
        	String kaisoEndDt = entity.getKaisoEnd();
        	if (!dateVerifier.validate(kaisoEndDt)) {
        		throw new InvalidInputException("店改装：終了日");
        	}
        	// 金額
            String kaisoHiyo = entity.getKaisoHiyo();
        	if (!codeVerifier11.validate(kaisoHiyo)) {
        		throw new InvalidInputException("店改装：金額");
        	}
        	// 内容
        	String kaisoNaiyo = entity.getKaisoNaiyo();
            if (!lengthVerifier200.validate(kaisoNaiyo)) {
        		throw new InvalidInputException("店改装：内容");
        	}
        }
        List listTO = dto.getListTO();
        for (Iterator ite = listTO.iterator(); ite.hasNext();) {
        	MstTO entity = (MstTO) ite.next();
        	//テイクオーバー日
        	String toDate = entity.getToDate();
        	if (!dateVerifier.validate(toDate)) {
        		throw new InvalidInputException("テイクオーバー：テイクオーバー日");
        	}
        	//前オーナーコード
        	String oldOnerCd = entity.getOldOnerCd();
        	if (!codeVerifierOner.validate(oldOnerCd)) {
        		throw new InvalidInputException("テイクオーバー：前オーナーコード");
        	}
            if (!lengthVerifier5.validate(oldOnerCd)) {
        		throw new InvalidInputException("テイクオーバー：前オーナーコード");
        	}
        	//前オーナー名称
        	String oldOnerName = entity.getOldOnerName();
            if (!lengthVerifier40.validate(oldOnerName)) {
        		throw new InvalidInputException("テイクオーバー：前オーナー名称");
        	}
        }
        List listSB = dto.getListSB();
        for (Iterator ite = listSB.iterator(); ite.hasNext();) {
        	MstSB entity = (MstSB) ite.next();
        	//スクラップ日
        	String toDate = entity.getSbDate();
        	if (!dateVerifier.validate(toDate)) {
        		throw new InvalidInputException("スクラップビルド：スクラップ日");
        	}
        	//前旧店舗コード
        	String oldMiseCd = entity.getOldMiseCd();
        	if (!codeVerifierMise.validate(oldMiseCd)) {
        		throw new InvalidInputException("スクラップビルド：旧店舗コード");
        	}
            if (!lengthVerifier5.validate(oldMiseCd)) {
        		throw new InvalidInputException("スクラップビルド：旧店舗コード");
        	}
        	//前旧店舗名称
        	String oldMiseName = entity.getOldMiseName();
            if (!lengthVerifier40.validate(oldMiseName)) {
        		throw new InvalidInputException("スクラップビルド：旧店舗名称");
        	}
        }
    }

    // 共通項目チェック
    private void checkCommon(MiseMaintenanceDto dto) {
        DateVerifier dateVerifier = new DateVerifier(DateVerifier.DATE_TYPE_YMD);
        LengthVerifier lengthVerifier200 = new LengthVerifier(200);

        /** 特性・営業時間 -------------------------------------------------*/
        chkKinkyuRenraku(dto);

        /** 地図情報 -------------------------------------------------------*/
        //タブ表示設定　******
        dto.setSelectedTab(3);

        //公式ホームページURL
        String url = dto.getTrnUrl().getUrl();
        if (!lengthVerifier200.validate(url)) {
            throw new InvalidInputException("地図情報：公式ホームページURL");
        }

        /** サービス実施状況 -----------------------------------------------*/
        //タブ表示設定　******
        dto.setSelectedTab(9);

        List listMstEventStatus = dto.getListMstEventStatus();
        for(int i = 0; i < listMstEventStatus.size(); i++){
            MstEventStatus entity = (MstEventStatus) listMstEventStatus.get(i);
//        for (Iterator ite = listEvent.iterator(); ite.hasNext();) {
//        	MstEventStatus entity = (MstEventStatus) ite.next();

            // 開始日
            String eventStartDt = entity.getEventStartDt();

            if (EVENT_BNRUI_TAKUHAI.equals(entity.getEventBnrui())) {
                //イベント分類＝０２（宅配）の場合
                if (!isNull(entity.getEventStartDt())
                        || !isNull(entity.getEventEndDt())
                        || !isNull(entity.getEventPatternCd())
                        || !isNull(entity.getHenkoDt()))
                {
                    //開始日、終了日、種別、適用日の何れかが入力された場合、全て必須
                    //「サービス実施状況：開始日」必須
                    if (isNull(eventStartDt)) {
                        throw new NotNullException("サービス実施状況：開始日");
                    }
                    //終了日に何も入力されなかった場合、'99991231'を挿入。
                    if (isNull(entity.getEventEndDt())) {
                        entity.setEventEndDt("99991231");
                    }
                    //「サービス実施状況：種別」必須
                    if(entity.getListEventPatternCd().size() != 0) {
                        String eventPatternCd = entity.getEventPatternCd();
                        if (eventPatternCd == null
                            || eventPatternCd.length() == 0
                            || eventPatternCd.equals("0")) {
                            throw new NotNullException("サービス実施状況：種別");
                        }
                    }
                    //「サービス実施状況：適用日」必須
                    if (isNull(entity.getHenkoDt())) {
                        throw new NotNullException("サービス実施状況：適用日");
                    }
                }
            }
            else {
                //実施状況が「1：実施」又は「2：一部実施」の場合
                if(entity.getEventStatus().equals("1")
                   || entity.getEventStatus().equals("2")){
                    //「サービス実施状況：開始日」必須
                    if (eventStartDt == null || eventStartDt.trim().length() == 0) {
                        throw new NotNullException("サービス実施状況：開始日");
                    }

                    //「種別」プルダウンに値が入っている場合
                    if(entity.getListEventPatternCd().size() != 0) {
                        //「種別」必須
                        String eventPatternCd = entity.getEventPatternCd();
                        if (eventPatternCd == null
                            || eventPatternCd.length() == 0
                            || eventPatternCd.equals("0")) {
                            throw new NotNullException("サービス実施状況：種別");
                        }
                    }

                    //終了日に何も入力されなかった場合、'99991231'を挿入。
                    if (entity.getEventEndDt().equals("")) {
                        entity.setEventEndDt("99991231");
                    }
                }
            }

        	if (!dateVerifier.validate(eventStartDt)) {
        		throw new InvalidInputException("サービス実施状況：開始日");
        	}
        	// 終了日
        	String eventEndDt = entity.getEventEndDt();
        	if (!dateVerifier.validate(eventEndDt)) {
        		throw new InvalidInputException("サービス実施状況：終了日");
        	}

            // 変更日
            String eventHenkoDt = entity.getHenkoDt();
            if (!dateVerifier.validate(eventHenkoDt)) {
                throw new InvalidInputException("サービス実施状況：適用日");
            }
            //備考
            String eventNotes = entity.getNotes();
            if (!lengthVerifier200.validate(eventNotes)) {
                throw new InvalidInputException("サービス実施状況：備考");
            }
        }

        /** メモ -------------------------------------------------------------*/
        //タブ表示設定　******
        dto.setSelectedTab(10);

        // 子ども１１０番情報
        String memo1 = dto.getMstMiseYobi().getYobiFree1();
        LengthVerifier lengthVerifier100 = new LengthVerifier(100);
        if (!lengthVerifier100.validate(memo1)) {
            throw new InvalidInputException("その他：子ども１１０番情報");
        }
        // メモ２
        String memo2 = dto.getMstMiseYobi().getYobiFree2();
        if (!lengthVerifier100.validate(memo2)) {
            throw new InvalidInputException("その他：休業情報");
        }
        // メモ３
        String memo3 = dto.getMstMiseYobi().getYobiFree3();
        if (!lengthVerifier100.validate(memo3)) {
            throw new InvalidInputException("その他：メモ３");
        }
        // メモ４
        String memo4 = dto.getMstMiseYobi().getYobiFree4();
        if (!lengthVerifier100.validate(memo4)) {
            throw new InvalidInputException("その他：メモ４");
        }
        // メモ５
        String memo5 = dto.getMstMiseYobi().getYobiFree5();
        if (!lengthVerifier100.validate(memo5)) {
            throw new InvalidInputException("その他：メモ５");
        }
    }


    /**
     * 2006/05/24追加
     * 空白行削除処理
     * @param dto
     */
    private void removeEmptyData(MiseMaintenanceDto dto){
        /** 物件情報履歴  ----------------------------------------------------*/
        List listMstBukken = dto.getListBukken();
        if (listMstBukken != null) {
            for (Iterator ite = listMstBukken.iterator(); ite.hasNext();) {
                MstBukken mstBukken = (MstBukken) ite.next();
                //更新日・家賃・家賃税区分・家主全ての項目が空白の場合、remove。
                if(mstBukken.getKeiyakuUpdate().equals("")
                        && mstBukken.getYachinZeiKbn().equals("")
                        && mstBukken.getKeiyakuYachin() == null
                        && mstBukken.getKeiyakuOner().equals("")){
                    ite.remove();
                }
            }
        }

        /** 賃貸店舗履歴 ------------------------------------------------------*/
        List listChintai = dto.getListChintai();
        if (listChintai != null) {
            for (Iterator ite = listChintai.iterator(); ite.hasNext();) {
                MstChintai mstChintai = (MstChintai) ite.next();
                //賃貸店舗種別・開始日・終了日全ての項目が空白の場合、remove。
                if(mstChintai.getMiseLeaseShu().equals("")
                        && mstChintai.getMiseLeaseStart().equals("")
                        && mstChintai.getMiseLeaseEnd().equals("")){
                    ite.remove();
                }
            }
        }

        /**店改装履歴 -------------------------------------------------------- */
        List listMiseKaiso = dto.getListMiseKaiso();
        if (listMiseKaiso != null) {
            for (Iterator ite = listMiseKaiso.iterator(); ite.hasNext();) {
                MstMiseKaiso mstMiseKaiso = (MstMiseKaiso) ite.next();
                //店改装・開始日・終了日全ての項目が空白の場合、remove。
                if(mstMiseKaiso.getKaisoHoshu().equals("")
                        && mstMiseKaiso.getKaisoSta().equals("")
                        && mstMiseKaiso.getKaisoEnd().equals("")){
                    ite.remove();
                }
            }
        }

        /**テイクオーバー履歴 ------------------------------------------------ */
        List listTO = dto.getListTO();
        if (listTO != null) {
            for (Iterator ite = listTO.iterator(); ite.hasNext();) {
                MstTO mstTO = (MstTO) ite.next();
                //テイクオーバー日・前オーナーコード・前オーナー名称
                //全ての項目が空白の場合、remove。
                if(mstTO.getToDate().equals("")
                        && mstTO.getOldOnerCd().equals("")
                        && mstTO.getOldOnerName().equals("")){
                    ite.remove();
                }
            }
        }

        /** スクラップビルド履歴 --------------------------------------------- */
        List listSB = dto.getListSB();
        if (listSB != null) {
            for (Iterator ite = listSB.iterator(); ite.hasNext();) {
                MstSB mstSB = (MstSB) ite.next();
                //スクラップ日・旧店舗コード・旧店舗名称の
                //全ての項目が空白の場合、remove。
                if(mstSB.getSbDate().equals("")
                        && mstSB.getOldMiseCd().equals("")
                        && mstSB.getOldMiseName().equals("")){
                    ite.remove();
                }
            }
        }

        /** スクラップビルド履歴 --------------------------------------------- */
        List listMstEventStatus = dto.getListMstEventStatus();
        if (listMstEventStatus != null) {
            for (Iterator ite = listMstEventStatus.iterator(); ite.hasNext();) {
                MstEventStatus mstEventStatus = (MstEventStatus) ite.next();
                //実施状況・開始日・終了日の
                //全ての項目が空白の場合、種別に空白をセット。
                //※種別だけ入力された場合はremove。
                if(mstEventStatus.getEventStatus().equals("")
                        && mstEventStatus.getEventStartDt().equals("")
                        && mstEventStatus.getEventEndDt().equals("")){
                    mstEventStatus.setEventPatternCd("");
                    mstEventStatus.setEventPatternName("");
                }
            }
        }
    }


    //
    private boolean isNull(String str) {
        return (str == null || "".equals(str.trim())) ? true : false;
    }
}
