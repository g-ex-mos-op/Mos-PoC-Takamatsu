package jp.co.isid.mos.bird.storemanage.msssurveydataref.action;

public interface MssSurveyDataRefAction {

    /**
     * ğŒ‰æ–Ê‹N“®i‰Šúˆ—j
     */
    public String initialize() throws Exception;
    
    public String executeStatus();

    public String clearErr();

    public String execute();

    public String upload();

    public String back();

    public String batchKick();
}
