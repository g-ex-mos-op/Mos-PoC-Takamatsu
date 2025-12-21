package jp.co.isid.mos.bird.bizsupport.similarshop.logic.impl;

import java.awt.Color;
import java.awt.Dimension;
import java.math.BigDecimal;
import java.text.NumberFormat;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import jp.co.isid.mos.bird.bizsupport.similarshop.dto.RuijitenReferenceDto;

import jp.co.isid.mos.bird.framework.control.BirdContext;
import jp.co.isid.mos.bird.framework.control.BirdUserInfo;
import jp.co.isid.mos.bird.framework.control.SessionListenerBean;
import jp.co.isid.mos.bird.framework.dto.GraphOutputDto;
import jp.co.isid.mos.bird.framework.entity.MstUser;
import jp.co.isid.mos.bird.framework.logic.GraphOutputLogic;
import jp.co.isid.mos.bird.framework.util.Calculator;
import jp.co.isid.mos.bird.framework.util.formatter.NumericFormatter;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.CategoryAxis;
import org.jfree.chart.axis.NumberAxis;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.plot.DatasetRenderingOrder;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.renderer.category.LineAndShapeRenderer;
import org.jfree.chart.title.TextTitle;
import org.jfree.data.DataUtilities;
import org.jfree.data.DefaultKeyedValues;
import org.jfree.data.category.CategoryDataset;
import org.jfree.data.general.DatasetUtilities;
import org.jfree.data.general.DefaultPieDataset;
import org.jfree.util.SortOrder;
import org.seasar.framework.container.S2Container;
import org.seasar.framework.container.factory.SingletonS2ContainerFactory;

/**
 * @author xnkusama
 */
public class GetRuijitenGraphLogicImpl implements GraphOutputLogic {

    /* ロジックID */
    private static final String LOGIC_ID = "BBS008L03";
    
    private static final String PATH = "examples/jfreechart/GraphSample.dicon";
    
    /* ファイル名取得 */
    public String getFileName(final GraphOutputDto graphOutputDto) {
        return null;
    }
    
    /* ユーザーID取得 */
    public String getUserId(final GraphOutputDto graphOutputDto) {
    	
    	S2Container container = SingletonS2ContainerFactory.getContainer();
        HttpServletRequest request = (HttpServletRequest) container
				.getComponent("request");
		BirdUserInfo birdUserInfo = (BirdUserInfo) request.getSession()
				.getAttribute("birdUserInfo");
		
		// MstUser取得
        MstUser mstUser = birdUserInfo.getMstUser();
        // ユーザータイプ判別
        String userId = mstUser.getUser_id();
    	
        return userId;
    }

    
    
    /* DTO */
    // 類似店照会用Dto //
    private RuijitenReferenceDto ruijitenReferenceDto;
    
	/////////////////
	/*     DTO     */
	/////////////////
//	///**
//	// * 類似店検索用dtoの設定
//	// * @return ruijitenReferenceDto を戻します。
//	// */
//	//public RuijitenReferenceDto getRuijitenReferenceDto() {
//		return ruijitenReferenceDto;
//	}
//	/**
//	 * 類似店検索用dtoの設定
//	 * @param ruijitenReferenceDto を設定。
//	 */
//	public void setRuijitenReferenceDto(RuijitenReferenceDto ruijitenReferenceDto) {
//		this.ruijitenReferenceDto = ruijitenReferenceDto;
//	}
	
	
    
    public JFreeChart createOutputData(final GraphOutputDto graphOutputDto) {

    	//dtoのキャスト(RuijitenReferenceDtoは、GraphOutputDtoをimplementsしているため、キャスト可能)
    	RuijitenReferenceDto dto = (RuijitenReferenceDto) graphOutputDto;
    	
        // 横軸
    	double[][] data = new double[][]
							   {{Calculator.percentage(dto.getMisePLData().getUriagegenka(), dto.getMisePLData().getUriagedaka(), 2).doubleValue(),
							   	 Calculator.percentage(dto.getMisePLData().getUriageSoRieki(), dto.getMisePLData().getUriagedaka(), 2).doubleValue(),
							   	 Calculator.percentage(dto.getMisePLData().getSalary(), dto.getMisePLData().getUriagedaka(), 2).doubleValue(),
							   	 Calculator.percentage(dto.getMisePLData().getYachin(), dto.getMisePLData().getUriagedaka(), 2).doubleValue(),
							   	 Calculator.percentage(dto.getMisePLData().getSuikouHi(), dto.getMisePLData().getUriagedaka(), 2).doubleValue(),
							   	 Calculator.percentage(dto.getMisePLData().getRoyalty(), dto.getMisePLData().getUriagedaka(), 2).doubleValue(),
							   	 Calculator.percentage(dto.getMisePLData().getKoukoku(), dto.getMisePLData().getUriagedaka(), 2).doubleValue(),
							   	 Calculator.percentage(dto.getMisePLData().getShoumou(), dto.getMisePLData().getUriagedaka(), 2).doubleValue(),
							   	 Calculator.percentage(dto.getMisePLData().getKeihiShokei(), dto.getMisePLData().getUriagedaka(), 2).doubleValue(),
							   	 Calculator.percentage(dto.getMisePLData().getShokyakuRieki(), dto.getMisePLData().getUriagedaka(), 2).doubleValue(),
							   	 Calculator.percentage(dto.getMisePLData().getGenkaShokyaku(), dto.getMisePLData().getUriagedaka(), 2).doubleValue(),
							   	 Calculator.percentage(dto.getMisePLData().getRieki(), dto.getMisePLData().getUriagedaka(), 2).doubleValue()
								},
							    {Calculator.percentage(dto.getMiseRuijiPLData().getUriagegenka(), dto.getMiseRuijiPLData().getUriagedaka(), 2).doubleValue(),
								 Calculator.percentage(dto.getMiseRuijiPLData().getUriageSoRieki(), dto.getMiseRuijiPLData().getUriagedaka(), 2).doubleValue(),
								 Calculator.percentage(dto.getMiseRuijiPLData().getSalary(), dto.getMiseRuijiPLData().getUriagedaka(), 2).doubleValue(),
								 Calculator.percentage(dto.getMiseRuijiPLData().getYachin(), dto.getMiseRuijiPLData().getUriagedaka(), 2).doubleValue(),
								 Calculator.percentage(dto.getMiseRuijiPLData().getSuikouHi(), dto.getMiseRuijiPLData().getUriagedaka(), 2).doubleValue(),
								 Calculator.percentage(dto.getMiseRuijiPLData().getRoyalty(), dto.getMiseRuijiPLData().getUriagedaka(), 2).doubleValue(),
								 Calculator.percentage(dto.getMiseRuijiPLData().getKoukoku(), dto.getMiseRuijiPLData().getUriagedaka(), 2).doubleValue(),
								 Calculator.percentage(dto.getMiseRuijiPLData().getShoumou(), dto.getMiseRuijiPLData().getUriagedaka(), 2).doubleValue(),
								 Calculator.percentage(dto.getMiseRuijiPLData().getKeihiShokei(), dto.getMiseRuijiPLData().getUriagedaka(), 2).doubleValue(),
								 Calculator.percentage(dto.getMiseRuijiPLData().getShokyakuRieki(), dto.getMiseRuijiPLData().getUriagedaka(), 2).doubleValue(),
								 Calculator.percentage(dto.getMiseRuijiPLData().getGenkaShokyaku(), dto.getMiseRuijiPLData().getUriagedaka(), 2).doubleValue(),
								 Calculator.percentage(dto.getMiseRuijiPLData().getRieki(), dto.getMiseRuijiPLData().getUriagedaka(), 2).doubleValue()
							    }
							   };
    	// 店舗名設定
    	String[] strMise = new String[]{dto.getTenpoData().getMiseNameKj(), "類似店"};

    	// 項目名設定
    	String[] strKoumoku = new String[]{"売上原価計","売上純利益","人件費","家賃地代","水道光熱費","ロイヤルティ","広告費","消耗品費","経費小計","償却前利益","減価償却費","税引前利益"};
    	
    	//CategoryDatasetオブジェクトの作成
    	CategoryDataset categorydataset = DatasetUtilities.createCategoryDataset(strMise, strKoumoku, data);

    	// タイトル、横軸タイトル、縦軸タイトル
        JFreeChart jfreechart = ChartFactory.createBarChart(dto.getPrevPlYm().substring(0,4)+"年"+dto.getPrevPlYm().substring(4,6)+"月 Ｐ／Ｌ 構成比", "", "[％]", categorydataset, PlotOrientation.HORIZONTAL, true, true, true);
        // グラフ画像の背景色
        jfreechart.setBackgroundPaint(Color.white);
        
        // グラフエリア
        CategoryPlot categoryplot = jfreechart.getCategoryPlot();
        // グラフ部分の背景色
        categoryplot.setBackgroundPaint(Color.lightGray);
        // グリッド線の色
        categoryplot.setRangeGridlinePaint(Color.white);
        
        // 軸の設定
        CategoryAxis categoryaxis = categoryplot.getDomainAxis();
        categoryaxis.setCategoryMargin(0.5);
        
        return jfreechart;
    }
    
    public JFreeChart createOutputData0(final GraphOutputDto graphOutputDto) {

    	//dtoのキャスト(RuijitenReferenceDtoは、GraphOutputDtoをimplementsしているため、キャスト可能)
    	RuijitenReferenceDto dto = (RuijitenReferenceDto) graphOutputDto;
    	
    	/* ↓ テスト用コード ↓*/
    	// テンポラリファイル自動削除テスト
    	//if (getHttpSession().getAttribute("listener") == null) {
    	//    getHttpSession().setAttribute("listener", new SessionListenerBean("12345", ""));
          
    	//    MstUser user = new MstUser();
    	//    user.setUser_id("12345");
    	//    getHttpSession().setAttribute("user", user);
    	//}
    	/* ↑ テスト用コード ↑ */
      
    	
//    	//
//        String val = "";
//        if(value != null){
//        	val = value.toString();
//        }
//
//        
//        
//        NumericFormatter formatter = new NumericFormatter(true, 2);
//        
//        BigDecimal kosehi = new BigDecimal("0");
//        if (val != null && !val.trim().equals("")) {
//            BigDecimal kingaku = new BigDecimal(val);
//            BigDecimal kingakuKei = getRuijitenReferenceDto().getMisePLData().getUriagedaka();
//            kosehi = Calculator.percentage(kingaku, kingakuKei, 2);
//        }
    	
    	
        // 横軸
    	double[][] data = new double[][]
							   {{Calculator.percentage(dto.getMisePLData().getUriage(), dto.getMisePLData().getUriagedaka(), 2).doubleValue(),
							   	 Calculator.percentage(dto.getMisePLData().getUriageSoRieki(), dto.getMisePLData().getUriagedaka(), 2).doubleValue(),
							   	 Calculator.percentage(dto.getMisePLData().getSalary(), dto.getMisePLData().getUriagedaka(), 2).doubleValue(),
							   	 Calculator.percentage(dto.getMisePLData().getYachin(), dto.getMisePLData().getUriagedaka(), 2).doubleValue(),
							   	 Calculator.percentage(dto.getMisePLData().getSuikouHi(), dto.getMisePLData().getUriagedaka(), 2).doubleValue(),
							   	 Calculator.percentage(dto.getMisePLData().getRoyalty(), dto.getMisePLData().getUriagedaka(), 2).doubleValue(),
							   	 Calculator.percentage(dto.getMisePLData().getKoukoku(), dto.getMisePLData().getUriagedaka(), 2).doubleValue(),
							   	 Calculator.percentage(dto.getMisePLData().getShoumou(), dto.getMisePLData().getUriagedaka(), 2).doubleValue(),
							   	 Calculator.percentage(dto.getMisePLData().getKeihiShokei(), dto.getMisePLData().getUriagedaka(), 2).doubleValue(),
							   	 Calculator.percentage(dto.getMisePLData().getShokyakuRieki(), dto.getMisePLData().getUriagedaka(), 2).doubleValue(),
							   	 Calculator.percentage(dto.getMisePLData().getGenkaShokyaku(), dto.getMisePLData().getUriagedaka(), 2).doubleValue(),
							   	 Calculator.percentage(dto.getMisePLData().getRieki(), dto.getMisePLData().getUriagedaka(), 2).doubleValue()
								},
							    {Calculator.percentage(dto.getMiseRuijiPLData().getUriage(), dto.getMiseRuijiPLData().getUriagedaka(), 2).doubleValue(),
								 Calculator.percentage(dto.getMiseRuijiPLData().getUriageSoRieki(), dto.getMiseRuijiPLData().getUriagedaka(), 2).doubleValue(),
								 Calculator.percentage(dto.getMiseRuijiPLData().getSalary(), dto.getMiseRuijiPLData().getUriagedaka(), 2).doubleValue(),
								 Calculator.percentage(dto.getMiseRuijiPLData().getYachin(), dto.getMiseRuijiPLData().getUriagedaka(), 2).doubleValue(),
								 Calculator.percentage(dto.getMiseRuijiPLData().getSuikouHi(), dto.getMiseRuijiPLData().getUriagedaka(), 2).doubleValue(),
								 Calculator.percentage(dto.getMiseRuijiPLData().getRoyalty(), dto.getMiseRuijiPLData().getUriagedaka(), 2).doubleValue(),
								 Calculator.percentage(dto.getMiseRuijiPLData().getKoukoku(), dto.getMiseRuijiPLData().getUriagedaka(), 2).doubleValue(),
								 Calculator.percentage(dto.getMiseRuijiPLData().getShoumou(), dto.getMiseRuijiPLData().getUriagedaka(), 2).doubleValue(),
								 Calculator.percentage(dto.getMiseRuijiPLData().getKeihiShokei(), dto.getMiseRuijiPLData().getUriagedaka(), 2).doubleValue(),
								 Calculator.percentage(dto.getMiseRuijiPLData().getShokyakuRieki(), dto.getMiseRuijiPLData().getUriagedaka(), 2).doubleValue(),
								 Calculator.percentage(dto.getMiseRuijiPLData().getGenkaShokyaku(), dto.getMiseRuijiPLData().getUriagedaka(), 2).doubleValue(),
								 Calculator.percentage(dto.getMiseRuijiPLData().getRieki(), dto.getMiseRuijiPLData().getUriagedaka(), 2).doubleValue()
							    }
							   };
    	// 店舗名設定
    	String[] strMise = new String[]{"成増店","類似店"};
    	// 項目名設定
    	String[] strKoumoku = new String[]{"売上原価計","売上純利益","人件費","家賃地代","水道光熱費","ロイヤルティ","広告費","消耗品費","経費小計","償却前利益","減価償却費","税引前利益"};
    	
    	//CategoryDatasetオブジェクトの作成
    	CategoryDataset categorydataset = DatasetUtilities.createCategoryDataset(strMise, strKoumoku, data);


    	// タイトル、横軸タイトル、縦軸タイトル
        JFreeChart jfreechart = ChartFactory.createBarChart("", "", "", categorydataset, PlotOrientation.HORIZONTAL, true, true, false);
        // サブタイトル
        jfreechart.addSubtitle(new TextTitle("2006年02月 Ｐ／Ｌ 構成比"));
        //jfreechart.addSubtitle(new TextTitle("As at 5 March 2003"));
        // グラフ画像の背景色
        jfreechart.setBackgroundPaint(Color.white);
        
        // グラフエリア
        CategoryPlot categoryplot = jfreechart.getCategoryPlot();
        // グラフ部分の背景色
        categoryplot.setBackgroundPaint(Color.lightGray);
        // グリッド線の色
        categoryplot.setRangeGridlinePaint(Color.white);
        // 軸の設定
        CategoryAxis categoryaxis = categoryplot.getDomainAxis();
        
        categoryaxis.setCategoryMargin(0.02);

        categoryaxis.setLowerMargin(0.02D);
        categoryaxis.setUpperMargin(0.02D);
        // 軸に整数配置
        //NumberAxis numberaxis = (NumberAxis)categoryplot.getRangeAxis();
        //numberaxis.setStandardTickUnits(NumberAxis.createIntegerTickUnits());
        //折れ線グラフ
        //LineAndShapeRenderer lineandshaperenderer = new LineAndShapeRenderer();
        //
//        org.jfree.data.category.CategoryDataset categorydataset1 = DatasetUtilities.createCategoryDataset("Cumulative", keyedvalues);
        NumberAxis numberaxis1 = new NumberAxis("[％]");
        numberaxis1.setNumberFormatOverride(NumberFormat.getPercentInstance());
        categoryplot.setRangeAxis(1, numberaxis1);
//        categoryplot.setDataset(1, categorydataset1);
        //折れ線グラフ
        //categoryplot.setRenderer(1, lineandshaperenderer);
        categoryplot.mapDatasetToRangeAxis(1, 1);
        categoryplot.setDatasetRenderingOrder(DatasetRenderingOrder.FORWARD);
        
        return jfreechart;
    }
    

    
    public int getFileType() {
        return FILE_TYPE_JPEG;
    }
    
    /* ファイルフルパス取得処理 */
    public String getFilePath() {
        String path = "";
        path = BirdContext.getProperty("fileProperty", 
                                       "imageTempPath");
        return path;
    }



    /**
     * 画像URL取得
     */
    public String getImageUrl(final GraphOutputDto graphOutputDto){
        
       return BirdContext.getProperty("fileProperty", "imageURL");
    }
    
    
    
/* ↓↓↓  一時ファイル削除のためのテスト用コード ↓↓↓ */
    /* HttpSession */
    //private HttpSession _httpSession = null;
    
    /**
     * HttpSession設定処理
     * @param response
     */
    //public void setHttpSession(final HttpSession session) {
    //    this._httpSession = session;
    //}
    //private HttpSession getHttpSession() {
    //    return _httpSession;
    //}
/* ↑↑↑  一時ファイル削除のためのコード ↑↑↑ */    
}