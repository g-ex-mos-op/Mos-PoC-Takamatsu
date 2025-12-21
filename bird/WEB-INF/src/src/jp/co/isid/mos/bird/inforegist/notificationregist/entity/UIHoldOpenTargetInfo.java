package jp.co.isid.mos.bird.inforegist.notificationregist.entity;

import java.sql.Timestamp;



/**
 * 公開対象保持通達情報
 * 
 * 更新日：2006/03/16 xkinu 排他制御対応
 * @author 
 *
 */
public class UIHoldOpenTargetInfo {
    
    public static final String TABLE = "BT02NTCM";
    /** 登録日 */
    public static final String regDate_COLUMN = "REG_DATE";
    /** SEQ */
    public static final String seq_COLUMN = "SEQ";
    /** 公開開始日 */
    public static final String pubDate_COLUMN = "PUB_DATE";
    /** カテゴリ */
    public static final String cateId_COLUMN = "CATE_ID";
    /** カテゴリ */
    public static final String cateName_COLUMN = "CATE_NAME";
    /** 通達管理No */
    public static final String kanriNo_COLUMN = "KANRI_NO";
    /** ファイル名 */
    public static final String fileName_COLUMN = "FILE_NAME";
    /** タイトル */
    public static final String title_COLUMN = "TITLE";
    /** サブタイトル */
    public static final String subTitle_COLUMN = "SUB_TITLE";
    /** 添付ファイルタイトル名 */
    public static final String attachName1_COLUMN = "ATTACH_NAME1";
    /** 添付ファイルタイトル名 */
    public static final String attachName2_COLUMN = "ATTACH_NAME2";
    /** 添付ファイルタイトル名 */
    public static final String attachName3_COLUMN = "ATTACH_NAME3";
    /** 添付ファイル名 */
    public static final String attachFl1_COLUMN = "ATTACH_FL1";
    /** 添付ファイル名 */
    public static final String attachFl2_COLUMN = "ATTACH_FL2";
    /** 添付ファイル名 */
    public static final String attachFl3_COLUMN = "ATTACH_FL3";
    /** 所属 */
    public static final String shozokuKbn_COLUMN = "SHOZOKU_KBN";
    /** 所属名称 */
    public static final String shozokuName_COLUMN = "SHOZOKU_NAME";
    /** 業態 */
    public static final String gyotaiKbn_COLUMN = "GYOTAI_KBN";
    /** 業態名称 */
    public static final String gyotaiKbnName_COLUMN = "GYOTAI_KBN_NAME";
    /** 業態別設定の有無：有無の文字列を返します */
    public static final String kobetsuUmu_COLUMN = "KOBETSU_UMU";
    /** 個店別設定の有無：有無の文字列を返します */
    public static final String miseUmu_COLUMN = "MISE_UMU";
    /** 個店別設定店舗数 */
    public static final String kotenCnt_COLUMN = "KOTEN_CNT";
    /** 削除フラグ */
    public static final String sakujoFlg_COLUMN = "SAKUJO_FLG";
    public static final String firstTmsp_COLUMN = "FIRST_TMSP";
    public static final String lastTmsp_COLUMN = "LAST_TMSP";
    
    /**
     * 登録日
     */
    private String regDate;
    /**
     * シーケンス番号
     */
    private String seq;
    /**
     * 公開開始日
     */
    private String pubDate;
    /**
     * カテゴリID
     */
    private String cateId;
    /**
     * カテゴリ名
     */
    private String cateName;
    /**
     * 通達管理NO
     */
    private String kanriNo;
    /**
     * タイトル
     */
    private String title;
    /**
     * サブタイトル
     */
    private String subTitle;
    /**
     * 添付ファイルタイトル名
     */
    private String attachName1;
    /**
     * 添付ファイルタイトル名
     */
    private String attachName2;
    /**
     * 添付ファイルタイトル名
     */
    private String attachName3;
    /**
     * 添付ファイル名
     */
    private String attachFl1;
    /**
     * 添付ファイル名
     */
    private String attachFl2;
    /**
     * 添付ファイル名
     */
    private String attachFl3;
    /**
     * 所属
     */
    private String shozokuKbn;
    /**
     * 所属名称
     */
    private String shozokuName;
    /**
     * 業態
     */
    private String gyotaiKbn;
    /**
     * 業態名称
     */
    private String gyotaiKbnName;
    /**
     * ファイル名
     */
    private String fileName;
    /**
     * リンクファイル名
     */
    private String linkFiles;
    /**
     * 所属
     */
    private String userNamekj;
    /**
     * 業態別設定有無
     */
    private String kobetsuUmu;
    /**
     * 公開対象支部
     */
    private String pubSibuName;
    
    /**
     * 個店別設定有無
     */
    private String miseUmu;
    
    /**
     * 個店別設定店舗数
     */
    private String kotenCnt;
    /**
     * 削除フラグ
     */
    private String sakujoFlg;
    /**
     * 修正時ﾀｲﾑｽﾀﾝﾌﾟ
     */
    private Timestamp firstTmsp;
    /**
     * 修正時ﾀｲﾑｽﾀﾝﾌﾟ
     */
    private Timestamp lastTmsp;
 	/**
	 * @return クラス変数kotenCnt を戻します。
	 */
	public String getKotenCnt() {
		return kotenCnt==null?"":kotenCnt;
	}
	/**
	 * @param kotenCnt を クラス変数kotenCntへ設定します。
	 */
	public void setKotenCnt(String kotenCnt) {
		this.kotenCnt = kotenCnt;
	}
	/**
	 * @return クラス変数linkFiles を戻します。
	 */
	public String getLinkFiles() {
		return linkFiles==null?"":linkFiles;
	}
	/**
	 * @param linkFiles を クラス変数linkFilesへ設定します。
	 */
	public void setLinkFiles(String linkFiles) {
		this.linkFiles = linkFiles;
	}
	/**
     * 登録日を取得します。
     * @return 登録日
     */
    public String getRegDate() {
        return (regDate == null) ? "" : regDate;
    }
    /**
     * 登録日を設定します。
     * @param regDate 登録日
     */
    public void setRegDate(String regDate) {
        this.regDate = regDate;
    }
    
    /**
     * シーケンス番号を取得します。
     * @return シーケンス番号
     */
    public String getSeq() {
        return (seq == null) ? "" : seq;
    }
    /**
     * シーケンス番号を設定します。
     * @param seq シーケンス番号
     */
    public void setSeq(String seq) {
        this.seq = seq;
    }
    
    /**
     * カテゴリIDを取得します。
     * @return カテゴリID
     */
    public String getCateId() {
        return (cateId == null) ? "" : cateId;
    }
    /**
     * カテゴリIDを設定します。
     * @param cateId カテゴリID
     */
    public void setCateId(String cateId) {
        this.cateId = cateId;
    }
    /**
     * カテゴリ名を取得します。
     * @return カテゴリ名
     */
    public String getCateName() {
        return (cateName == null) ? "" : cateName;
    }
    /**
     * カテゴリ名を設定します。
     * @param cateName カテゴリ名
     */
    public void setCateName(String cateName) {
        this.cateName = cateName;
    }
    /**
     * タイトルを取得します。
     * @return タイトル
     */
    public String getTitle() {
        return (title == null) ? "" : title;
    }
    /**
     * タイトルを設定します。
     * @param title タイトル
     */
    public void setTitle(String title) {
        this.title = title;
    }
    
    /**
     * サブタイトルを取得します。
     * @return サブタイトル
     */
    public String getSubTitle() {
        return (subTitle == null) ? "" : subTitle;
    }
    /**
     * サブタイトルを設定します。
     * @param subTitle サブタイトル
     */
    public void setSubTitle(String subTitle) {
        this.subTitle = subTitle;
    }
    
    /**
     * 公開開始日を取得します。
     * @return 公開開始日
     */
    public String getPubDate() {
        return (pubDate == null) ? "" : pubDate;
    }
    /**
     * 公開開始日を設定します。
     * @param pubDate 公開開始日
     */
    public void setPubDate(String pubDate) {
        this.pubDate = pubDate;
    }
    /**
     * ユーザ名称を取得します。
     * @return ユーザ名称
     */
    public String getUserNamekj() {
        return (userNamekj == null) ? "" : userNamekj;
    }
    /**
     * ユーザ名称を設定します。
     * @param userNamekj ユーザ名称
     */
    public void setUserNamekj(String userNamekj) {
        this.userNamekj = userNamekj;
    }
    /**
     * 通達管理NOを取得します。
     * @return 通達管理NO
     */
    public String getKanriNo() {
        return (kanriNo == null) ? "" : kanriNo;
    }
    /**
     * 通達管理NOを設定します。
     * @param kanriNo 通達管理NO
     */
    public void setKanriNo(String kanriNo) {
        this.kanriNo = kanriNo;
    }
    
    /**
     * ファイル名を取得します。
     * @return ファイル名
     */
    public String getFileName() {
        return (fileName == null) ? "" : fileName;
    }
    /**
     * ファイル名を設定します。
     * @param fileName ファイル名
     */
    public void setFileName(String fileName) {
        this.fileName = fileName;
    }
	/**
	 * @return クラス変数shozokuKbn を戻します。
	 */
	public String getShozokuKbn() {
		return shozokuKbn==null?"":shozokuKbn;
	}
	/**
	 * @param shozokuKbn を クラス変数shozokuKbnへ設定します。
	 */
	public void setShozokuKbn(String shozokuKbn) {
		this.shozokuKbn = shozokuKbn;
	}
	/**
	 * @return クラス変数shozokuName を戻します。
	 */
	public String getShozokuName() {
		return shozokuName==null?"":shozokuName;
	}
	/**
	 * @param shozokuName を クラス変数shozokuNameへ設定します。
	 */
	public void setShozokuName(String shozokuName) {
		this.shozokuName = shozokuName;
	}
	/**
	 * @return クラス変数gyotaiKbn を戻します。
	 */
	public String getGyotaiKbn() {
		return gyotaiKbn==null?"":gyotaiKbn;
	}
	/**
	 * @param gyotaiKbn を クラス変数gyotaiKbnへ設定します。
	 */
	public void setGyotaiKbn(String gyotaiKbn) {
		this.gyotaiKbn = gyotaiKbn;
	}
	/**
	 * @return クラス変数gyotaiName を戻します。
	 */
	public String getGyotaiKbnName() {
		return gyotaiKbnName==null?"":gyotaiKbnName;
	}
	/**
	 * @param gyotaiKbnName を クラス変数gyotaiNameへ設定します。
	 */
	public void setGyotaiKbnName(String gyotaiName) {
		this.gyotaiKbnName = gyotaiName;
	}
	/**
	 * @return クラス変数kobetsuUmu を戻します。
	 */
	public String getKobetsuUmu() {
		return kobetsuUmu==null?"":kobetsuUmu;
	}
	/**
	 * @param kobetsuUmu を クラス変数kobetsuUmuへ設定します。
	 */
	public void setKobetsuUmu(String kobetsuUmu) {
		this.kobetsuUmu = kobetsuUmu;
	}
	/**
	 * @return クラス変数miseUmu を戻します。
	 */
	public String getMiseUmu() {
		return miseUmu==null?"":miseUmu;
	}
	/**
	 * @param miseUmu を クラス変数miseUmuへ設定します。
	 */
	public void setMiseUmu(String miseUmu) {
		this.miseUmu = miseUmu;
	}
	/**
	 * @return クラス変数pubSibuName を戻します。
	 */
	public String getPubSibuName() {
		return pubSibuName==null?"":pubSibuName;
	}
	/**
	 * @param pubSibuName を クラス変数pubSibuNameへ設定します。
	 */
	public void setPubSibuName(String pubSibuName) {
		this.pubSibuName = pubSibuName;
	}
	/**
	 * @return クラス変数attachFl1 を戻します。
	 */
	public String getAttachFl1() {
		return attachFl1;
	}
	/**
	 * @param attachFl1 を クラス変数attachFl1へ設定します。
	 */
	public void setAttachFl1(String attachFl1) {
		this.attachFl1 = attachFl1;
	}
	/**
	 * @return クラス変数attachFl2 を戻します。
	 */
	public String getAttachFl2() {
		return attachFl2;
	}
	/**
	 * @param attachFl2 を クラス変数attachFl2へ設定します。
	 */
	public void setAttachFl2(String attachFl2) {
		this.attachFl2 = attachFl2;
	}
	/**
	 * @return クラス変数attachFl3 を戻します。
	 */
	public String getAttachFl3() {
		return attachFl3;
	}
	/**
	 * @param attachFl3 を クラス変数attachFl3へ設定します。
	 */
	public void setAttachFl3(String attachFl3) {
		this.attachFl3 = attachFl3;
	}
	/**
	 * @return クラス変数attachName1 を戻します。
	 */
	public String getAttachName1() {
		return attachName1;
	}
	/**
	 * @param attachName1 を クラス変数attachName1へ設定します。
	 */
	public void setAttachName1(String attachName1) {
		this.attachName1 = attachName1;
	}
	/**
	 * @return クラス変数attachName2 を戻します。
	 */
	public String getAttachName2() {
		return attachName2;
	}
	/**
	 * @param attachName2 を クラス変数attachName2へ設定します。
	 */
	public void setAttachName2(String attachName2) {
		this.attachName2 = attachName2;
	}
	/**
	 * @return クラス変数attachName3 を戻します。
	 */
	public String getAttachName3() {
		return attachName3;
	}
	/**
	 * @param attachName3 を クラス変数attachName3へ設定します。
	 */
	public void setAttachName3(String attachName3) {
		this.attachName3 = attachName3;
	}
	/**
	 * 添付ファイル名取得処理
	 * 
	 * インデックスは0からです。
	 * @param index
	 * @return
	 */
	public String getTempFileName(int index) {
		String tempFileName = "";
		switch (index) {
		case 0:
			tempFileName = getAttachFl1();
			break;
		case 1:
			tempFileName = getAttachFl2();
			break;
		case 2:
			tempFileName = getAttachFl3();
			break;

		default:
			break;
		}
		return tempFileName;
	}
	/**
	 * @return クラス変数sakujoFlg を戻します。
	 */
	public String getSakujoFlg() {
		return sakujoFlg;
	}
	/**
	 * @param sakujoFlg を クラス変数sakujoFlgへ設定します。
	 */
	public void setSakujoFlg(String sakujoFlg) {
		this.sakujoFlg = sakujoFlg;
	}
	/**
	 * @return クラス変数firstTmsp を戻します。
	 */
	public Timestamp getFirstTmsp() {
		return firstTmsp;
	}
	/**
	 * @param firstTmsp を クラス変数firstTmspへ設定します。
	 */
	public void setFirstTmsp(Timestamp firstTmsp) {
		this.firstTmsp = firstTmsp;
	}
	/**
	 * @return クラス変数lastTmsp を戻します。
	 */
	public Timestamp getLastTmsp() {
		return lastTmsp;
	}
	/**
	 * @param lastTmsp を クラス変数lastTmspへ設定します。
	 */
	public void setLastTmsp(Timestamp lastTmsp) {
		this.lastTmsp = lastTmsp;
	}
}
