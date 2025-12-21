package jp.co.isid.mos.bird.bizreport.camprank.logic.impl;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import jp.co.isid.mos.bird.bizreport.camprank.common.CampRankConst;
import jp.co.isid.mos.bird.bizreport.camprank.dto.CampRankCondDto;
import jp.co.isid.mos.bird.bizreport.camprank.entity.UICampRankData;
import jp.co.isid.mos.bird.bizreport.common.code.UserType;
import jp.co.isid.mos.bird.framework.control.BirdUserInfo;
import jp.co.isid.mos.bird.framework.dto.CsvOutputDto;
import jp.co.isid.mos.bird.framework.logic.CsvOutputLogic;
import jp.co.isid.mos.bird.framework.util.formatter.DateFormatter;
import jp.co.isid.mos.bird.framework.util.formatter.NumericFormatter;

/**
 * CVSダウンロード
 * @author xnkusama
 *
 */
public class CsvLogicImpl implements CsvOutputLogic {
    /** ロジックID */    
    public static final String LOGIC_ID = "BBR013L03";

    /** Formatter */
    private NumericFormatter numFmtdgt0 = new NumericFormatter(true, 0, true);
    private NumericFormatter numFmtdgt2 = new NumericFormatter(true, 2);
    
    /**
     * CSVファイル名取得
     */
    public String getFileName(CsvOutputDto csvOutputDto) {
        return "CAMPRANK.csv";
    }

    /**
     * CVSデータ取得
     */
    public List getOutputData(CsvOutputDto csvOutputDto) {
        List listCsv = new ArrayList();
        CampRankCondDto dto = (CampRankCondDto) csvOutputDto;
        // ベスト１００データ
        // WindowIDに対応する結果データが存在するか確認
        List listData = dto.getListResult();
        
        // CSVヘッダ
        listCsv.addAll(makeCsvHeader(dto));
        // CSVデータへ加工
        listCsv.addAll(makeCsvData(listData, dto));
        
        return listCsv;
    }

    /**
     * 事前処理
     */
    public void validate(CsvOutputDto csvOutputDto) {
        // 処理無し
    }

    /**
     * CSVヘッダ部分データ作成
     * @param dto
     * @return
     */
    private List makeCsvHeader(CampRankCondDto dto) {
        List listHeader = new ArrayList();
        //1行目
        List list1 = new ArrayList();
        list1.add("キャンペーン：");
        list1.add(dto.getTargetMstCampDate().getCampTitle());
        list1.add("順位：");
        list1.add(dto.getCondRankName());
        //list1.add(dto.getTargetMstCampDate().getCampTitle());
        listHeader.add(list1);
        
        //2行目
        List list2 = new ArrayList();
        DateFormatter dateFormatter = new DateFormatter(DateFormatter.DATE_TYPE_YMD, DateFormatter.PATTERN_SLASH);
        list2.add("対象期間：");
        String taishoKikan = "";
        if (CampRankConst.SEARCH_MODE_TAISHOBI.equals(dto.getCondMode())) {
            taishoKikan = dateFormatter.format(dto.getCondTargetDt(), true);
        }
        else {
            taishoKikan = dateFormatter.format(dto.getTargetMstCampDate().getCampFrom(), true)
                        + " ～ "
                        + dateFormatter.format(dto.getCondTargetDt(), true);
        }
        list2.add(taishoKikan);
        list2.add("対象店舗数：");
        list2.add(numFmtdgt0.format(dto.getTaishoTenpoCount()));
        listHeader.add(list2);
        
        //3行目、4行目
        listHeader.add(new ArrayList());
        listHeader.add(new ArrayList());
        
        //5行目
        List list5 = new ArrayList();
        list5.add("順位");
        list5.add("支部名称");
        list5.add("店コード");
        list5.add("店名称");
        list5.add("販売個数");
        if (!isLimitUser(dto.getBirdUserInfo())) {
            list5.add("販売金額");
        }
        list5.add("金額構成比");
        if (!isLimitUser(dto.getBirdUserInfo())) {
            list5.add("売上");
            list5.add("前年売上");
        }
        list5.add("前年比");
        listHeader.add(list5);
        
        return listHeader;
    }
    
    /**
     * CSVメインデータ部分作成
     * @param listData
     * @return
     */
    private List makeCsvData(List listData, CampRankCondDto dto) {
        List listCsv = new ArrayList();
        
        for (Iterator ite = listData.iterator(); ite.hasNext();) {
            UICampRankData entity = (UICampRankData) ite.next();
            List listRow = new ArrayList();
            listRow.add(entity.getRank());
            listRow.add(entity.getSibuName());
            listRow.add(entity.getMiseCd());
            listRow.add(entity.getMiseNameKj());
            listRow.add(numFmtdgt0.format(entity.getHanbaiKosu()));
            if (!isLimitUser(dto.getBirdUserInfo())) {
                listRow.add(numFmtdgt0.format(entity.getHanbaiKin()));
            }
            listRow.add(entity.getKingakuKoseiHi());
            if (!isLimitUser(dto.getBirdUserInfo())) {
                listRow.add(numFmtdgt0.format(entity.getUriage()));
                listRow.add(numFmtdgt0.format(entity.getZenUriage()));
            }
            listRow.add(numFmtdgt2.format(entity.getZennenHi()));
            listCsv.add(listRow);
        }
        return listCsv;
    }
    
    /**
     * 制限ユーザーの判別
     * オーナーか店舗ユーザー、本部ユーザーでリモートアクセスの場合
     * 制限ユーザーとなる
     * @param birdUserInfo
     * @return
     */
    private boolean isLimitUser(BirdUserInfo birdUserInfo) {
        boolean limitFlg = false;
        if (UserType.ONER.equals(birdUserInfo.getMstUser().getUserTypeCd())
                || UserType.TENPO.equals(birdUserInfo.getMstUser().getUserTypeCd())
                || (UserType.HONBU.equals(birdUserInfo.getMstUser().getUserTypeCd()) && birdUserInfo.isLimit()))
        {
            limitFlg = true;
        }
        return limitFlg;
    }
    
}