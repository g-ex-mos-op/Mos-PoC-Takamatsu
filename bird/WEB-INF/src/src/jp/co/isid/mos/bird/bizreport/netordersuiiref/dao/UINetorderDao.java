/**
 *
 */
package jp.co.isid.mos.bird.bizreport.netordersuiiref.dao;

import java.math.BigDecimal;
import java.util.List;

import jp.co.isid.mos.bird.bizreport.common.suiiref.dto.SuiiRefParameterDto;
import jp.co.isid.mos.bird.bizreport.netordersuiiref.entity.UINetorder;
import jp.co.isid.mos.bird.framework.control.BirdDateInfo;
import jp.co.isid.mos.bird.framework.control.BirdUserInfo;

/**
 * DAO【ネット注文売上推移】
 * 作成日:
 *
 * @author xkinu
 *
 */
public interface UINetorderDao {
	public static final Class BEAN = UINetorder.class;

    public static final String selectNipo_ARGS = "birdUserInfo, birdDateInfo, paramsDto, zdsColmunName";
    public static final String selectNipoFuture_ARGS = "birdUserInfo, birdDateInfo, paramsDto, futureFrom, futureTo, zennenYm, zennenFrom, zennenTo, sysDate, sysdateGetumatu, togetuGessho, zennenGessho";
    public static final String selectNipoLeap0229_ARGS = "birdUserInfo, birdDateInfo, paramsDto";
    public static final String selectNipoOner_ARGS = "birdUserInfo, birdDateInfo, paramsDto";
    public static final String selectNipoOnerFuture_ARGS = "birdUserInfo, birdDateInfo, paramsDto, futureFrom, futureTo, zennenYm, zennenFrom, zennenTo, sysDate, sysdateGetumatu, togetuGessho, zennenGessho";
    public static final String selectGepo_ARGS = "birdUserInfo, birdDateInfo, paramsDto, kikanFrom, kikanTo, zdsColmunName";
    public static final String selectGepoFuture_ARGS = "birdUserInfo, birdDateInfo, paramsDto, kijunYm, futureFrom, futureTo, zennenFrom, zennenTo";
    public static final String selectGepoOner_ARGS = "paramsDto";
    public static final String selectGepoTogetuYosan_ARGS = "birdUserInfo, birdDateInfo, paramsDto, togetuYmdFrom, togetuYmdTo";
    /**
     * 日次検索
     *
     * @param birdUserInfo
     * @param birdDateInfo
     * @param paramsDto
     * @param zdsColmunName 前年データ種別カラム名
     * @return
     */
    public List selectNipo(BirdUserInfo birdUserInfo, BirdDateInfo birdDateInfo, SuiiRefParameterDto paramsDto, String zdsColmunName);
    /**
     * 未来日次検索処理
     *
     * 未来日の前年実績は、一世代のみの店番継承を考慮する。
     * 一年間に２度以上店番継承が行われた場合は、前年実績は取得できない
     * @paramsDto birdUserInfo
     * @paramsDto birdDateInfo
     * @paramsDto paramsDto
     * @paramsDto futureFrom
     * @paramsDto futureTo
     * @paramsDto zennenYm
     * @paramsDto zennenFrom
     * @paramsDto zennenTo
     * @paramsDto sysDate システム日付 yyyyMMdd
     * @paramsDto sysdateGetumatu システム日付の月末 yyyyMMdd
     * @paramsDto togetuGessho 当月月初 yyyyMMdd
     * @paramsDto zennenGessho 前年月初 yyyyMMdd
     * @return
     */
    public List selectNipoFuture(BirdUserInfo birdUserInfo, BirdDateInfo birdDateInfo, SuiiRefParameterDto paramsDto
    		, String futureFrom, String futureTo, String zennenYm, String zennenFrom, String zennenTo
    		, String sysDate, String sysdateGetumatu, String togetuGessho, String zennenGessho);
    /**
     * 前年うるう日情報検索
     *
     * @paramsDto birdUserInfo
     * @paramsDto birdDateInfo
     * @paramsDto paramsDto
     * @return
     */
    public List selectNipoLeap0229(BirdUserInfo birdUserInfo, BirdDateInfo birdDateInfo, SuiiRefParameterDto paramsDto);
    /**
     * オーナー用日次検索
     * @paramsDto birdUserInfo
     * @paramsDto birdDateInfo
     * @paramsDto paramsDto
     * @return
     */
    public List selectNipoOner(BirdUserInfo birdUserInfo, BirdDateInfo birdDateInfo, SuiiRefParameterDto paramsDto);
    /**
     * オーナー用未来日次検索
     *
     * @paramsDto birdUserInfo
     * @paramsDto birdDateInfo
     * @paramsDto paramsDto
     * @paramsDto futureFrom
     * @paramsDto futureTo
     * @paramsDto zennenYm
     * @paramsDto zennenFrom
     * @paramsDto zennenTo
     * @paramsDto sysDate システム日付 yyyyMMdd
     * @paramsDto sysdateGetumatu システム日付の月末 yyyyMMdd
     * @paramsDto togetuGessho 当月月初 yyyyMMdd
     * @paramsDto zennenGessho 前年月初 yyyyMMdd
     * @return
     */
    public List selectNipoOnerFuture(
    		BirdUserInfo birdUserInfo, BirdDateInfo birdDateInfo
    		, SuiiRefParameterDto paramsDto
    		, String futureFrom, String futureTo
    		, String zennenYm, String zennenFrom, String zennenTo
    		, String sysDate, String sysdateGetumatu
    		, String togetuGessho, String zennenGessho);
    /**
     * 月次検索
     *
     * @param birdUserInfo
     * @param birdDateInfo
     * @param paramsDto
     * @param kikanFrom
     * @param kikanTo
     * @param zdsColmunName
     * @return
     */
    public List selectGepo(BirdUserInfo birdUserInfo, BirdDateInfo birdDateInfo
    		, SuiiRefParameterDto paramsDto
    		, String kikanFrom, String kikanTo
    		, String zdsColmunName);
    /**
     * 未来月次検索
     *
     * 未来日の値は前年同月データから取得します。
     * @param birdUserInfo
     * @param birdDateInfo
     * @param paramsDto
     * @param kijunYm
     * @param futureFrom 未来開始年月
     * @param futureTo   未来終了年月
     * @param zennenFrom 前年開始年月
     * @param zennenTo   前年終了年月
     * @return
     */
    public List selectGepoFuture(BirdUserInfo birdUserInfo, BirdDateInfo birdDateInfo
    		, SuiiRefParameterDto paramsDto, String kijunYm
    		, String futureFrom, String futureTo, String zennenFrom, String zennenTo);
    /**
     * オーナー用月次検索
     *
     * @paramsDto paramsDto
     * @return
     */
    public List selectGepoOner(SuiiRefParameterDto paramsDto);
    /**
     * 当月予算取得
     *
     * @param birdUserInfo
     * @param birdDateInfo
     * @param paramsDto
     * @param togetuYmdFrom
     * @param togetuYmdTo
     * @return
     */
    public BigDecimal selectGepoTogetuYosan(BirdUserInfo birdUserInfo, BirdDateInfo birdDateInfo
    		, SuiiRefParameterDto paramsDto
    		, String togetuYmdFrom, String togetuYmdTo);

}