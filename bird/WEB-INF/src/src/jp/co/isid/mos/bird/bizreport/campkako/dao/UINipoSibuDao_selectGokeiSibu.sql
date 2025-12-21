select 
       company_cd,
       HONBU_CD,
       rtrim(HONBU_NAME) as honbu_name,
       JIGYOU_CD,
       rtrim(JIGYOU_NAME) as jigyou_name,
       SLAREA_CD,
       rtrim(SLAREA_NAME) as slarea_name,
       rtrim(sibu_cd) as sibu_cd,
       taisho_tenpo_cnt,
       menu_uriage,
       kazu_kei,
       uriage,
       kyakusu,
       uriage_zen,
       kyakusu_zen,
       CASE WHEN HONBU_CD IS NULL THEN '8_TOTAL' 
            WHEN JIGYOU_CD IS NULL THEN '5_HONBU_TOTAL' 
            WHEN SLAREA_CD IS NULL THEN '4_JIGYOU_TOTAL' 
            WHEN SIBU_CD IS NULL THEN '3_SLAREA_TOTAL' 
            ELSE '0' END AS ROW_TYPE,
       RTRIM(CASE WHEN HONBU_CD IS NULL THEN 'ëççáåv' 
            WHEN JIGYOU_CD IS NULL THEN HONBU_NAME 
            WHEN SLAREA_CD IS NULL THEN JIGYOU_NAME 
            WHEN SIBU_CD IS NULL THEN SLAREA_NAME 
            ELSE rtrim(SIBU_NAME) END) AS SIBU_NAME
from
(
select bn01.company_cd,
       bn01.HONBU_CD,
       bn01.HONBU_NAME,
       bn01.JIGYOU_CD,
       bn01.JIGYOU_NAME,
       bn01.SLAREA_CD,
       bn01.SLAREA_NAME,
       bn01.sibu_cd,
       bn01.sibu_name,
       bn01.taisho_tenpo_cnt,
       bd09.menu_uriage,
       bd09.kazu_kei,
       bn01.uriage,
       bn01.kyakusu,
       bn01.uriage_zen,
       bn01.kyakusu_zen
from  
     (
       select bn01sub.company_cd
       ,      BM10.HONBU_CD
       ,      BM10.HONBU_NAME
       ,      BM10.JIGYOU_CD
       ,      BM10.JIGYOU_NAME
       ,      BM10.SLAREA_CD
       ,      BM10.SLAREA_NAME
       ,      bm10.sibu_cd
       ,      BM10.SIBU_NAME
       ,      COUNT(DISTINCT (CASE WHEN OPEN_KBN = 1 THEN bn01sub.MISE_CD ELSE null END)) as TAISHO_TENPO_CNT
       ,      sum(bn01sub.uriage) as uriage
       ,      sum(bn01sub.kyakusu) as kyakusu
       ,      sum(bn01sub.uriage_zen) as uriage_zen
       ,      sum(bn01sub.kyakusu_zen) as kyakusu_zen
       from 
       (    
         select bn01.company_cd
         ,      bn01.mise_cd
         ,      max(open_kbn) as open_kbn
         ,      sum(bt60.uriage) as uriage
         ,      sum(bt60.kyakusu) as kyakusu
         ,      sum(bt60.uriage_zen_doyo) as uriage_zen
         ,      sum(bt60.kyakusu_zen_doyo) as kyakusu_zen
         from   bn01dten bn01
         ,      bt60znip bt60 
         where  bn01.company_cd = /*companyCd*/'00'
         and    bn01.eigyo_dt between /*campFrom*/'20071201' and /*campTo*/'20071231'
/*IF "1".equals(tenpoShubetu) || "3".equals(tenpoShubetu) */
         and    bn01.KBN1 = /*tenpoShubetu*/'1'
/*END*/
/*IF "2".equals(tenpoShubetu) */
         and    bn01.KBN1 IN ('1', '2')
/*END*/
         and    bn01.company_cd = bt60.company_cd
         and    bn01.eigyo_dt   = bt60.eigyo_dt
         and    bn01.mise_cd    = bt60.mise_cd
         and    bt60.oldm_flg <> '1'
         and    OPEN_KBN = 1
         group by bn01.company_cd,bn01.mise_cd
       ) bn01sub
       ,    bm65cpms bm65
       ,    bm01tenm bm01
       ,    bm10gsib bm10
       where bm65.camp_id = /*campId*/'200832'
       and   bm65.company_cd = bn01sub.company_cd
       and   bm65.mise_cd = bn01sub.mise_cd
       and   bm65.company_cd = bm01.company_cd
       and   bm65.mise_cd = bm01.mise_cd
       and   bm01.company_cd = bm10.company_cd
       and   bm10.sibu_cd     = bm01.sibu_cd
/*IF userTypeCd == "01" && limitFlg == true */
       and  bm01.sibu_cd in (
            select   bm51.sibu_cd
            from     bm51svsb bm51
            where  bm51.company_cd = /*companycd*/'00'
            and    bm51.sv_cd      = /*userid*/'99990005'
            group by bm51.sibu_cd
       )
/*END*/
       group by rollup (
         (Bn01sub.COMPANY_CD, BM10.HONBU_CD, BM10.HONBU_NAME)
       , (BM10.JIGYOU_CD, BM10.JIGYOU_NAME)
       , (BM10.SLAREA_CD, BM10.SLAREA_NAME)
       , (bm10.sibu_cd,BM10.SIBU_NAME)
       )       
     ) bn01 
     left join 
     (
       select bm01.company_cd
       ,      BM10.HONBU_CD
       ,      BM10.HONBU_NAME
       ,      BM10.JIGYOU_CD
       ,      BM10.JIGYOU_NAME
       ,      BM10.SLAREA_CD
       ,      BM10.SLAREA_NAME
       ,      bm10.sibu_cd
       ,      BM10.SIBU_NAME
       ,      sum(bd09.uriage) as menu_uriage
       ,      sum(bd09.kazu_kei * (case when bm69.conv_value is null then 1 else bm69.conv_value end)) as kazu_kei
       from   bd09cppr bd09,
              bm01tenm bm01,
              bm10gsib bm10,
              bm65cpms bm65,
              bm61cpmn bm61
              left outer join bm69symp bm69 on (bm61.camp_id = bm69.camp_id and bm61.menu_cd = bm69.menu_cd)
       where  bd09.camp_id = /*campId*/'200832'
       and   bd09.company_cd = /*companyCd*/'00'
/*IF "1".equals(tenpoShubetu) || "3".equals(tenpoShubetu) */
       AND   bd09.TENPO_SHU = /*tenpoShubetu*/'1'
/*END*/
/*IF "2".equals(tenpoShubetu) */
       AND   bd09.TENPO_SHU IN ('1', '2')
/*END*/
/*IF userTypeCd == "01" && limitFlg == true */
       and  bm01.sibu_cd in (
            select   bm51.sibu_cd
            from     bm51svsb bm51
            where  bm51.company_cd = /*companycd*/'00'
            and    bm51.sv_cd      = /*userid*/'99990005'
            group by bm51.sibu_cd
       )
/*END*/
       and   bd09.camp_id = bm65.camp_id
       and   bd09.company_cd = bm65.company_cd
       and   bd09.mise_cd    = bm65.mise_cd
       and   bm01.company_cd = bm65.company_cd
       and   bm01.mise_cd = bm65.mise_cd
       and   bm01.company_cd = bm10.company_cd
       and   bm01.sibu_cd = bm10.sibu_cd
       and   bd09.camp_id = bm61.camp_id
       and   bd09.menu_cd = bm61.menu_cd
       group by rollup (
          (BM01.COMPANY_CD, BM10.HONBU_CD, BM10.HONBU_NAME)
        , (BM10.JIGYOU_CD, BM10.JIGYOU_NAME)
        , (BM10.SLAREA_CD, BM10.SLAREA_NAME)
        , (bm10.sibu_cd,BM10.SIBU_NAME)
       )       
     ) 
     bd09 on (
        (bn01.COMPANY_CD = bd09.COMPANY_CD or (bn01.COMPANY_CD is null and  bd09.COMPANY_Cd is null))
        AND   (Bn01.HONBU_CD = Bd09.HONBU_CD OR (Bn01.HONBU_CD IS NULL AND Bd09.HONBU_CD IS NULL))
        AND   (Bn01.JIGYOU_CD = Bd09.JIGYOU_CD OR (Bn01.JIGYOU_CD IS NULL AND Bd09.JIGYOU_CD IS NULL))
        AND   (Bn01.SLAREA_CD = Bd09.SLAREA_CD OR (Bn01.SLAREA_CD IS NULL AND Bd09.SLAREA_CD IS NULL))
        and (bn01.sibu_cd = bd09.sibu_cd or (bn01.sibu_cd is null and bd09.sibu_cd is null))
     )
) as campData
order by 
    company_cd,
    HONBU_CD,
    JIGYOU_CD,
    SLAREA_CD,
    sibu_cd