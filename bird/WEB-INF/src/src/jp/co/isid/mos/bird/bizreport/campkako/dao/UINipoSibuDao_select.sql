select 
       company_cd,
       HONBU_CD,
       HONBU_NAME,
       JIGYOU_CD,
       JIGYOU_NAME,
       SLAREA_CD,
       SLAREA_NAME,
       rtrim(sibu_cd) as sibu_cd,
/*IF !menuTotaledKbn.equals("TOTAL")*/
       case when (sibu_name is null and pc10.menu_name_kj is null) then 'ëççáåv'
--ELSE
       case when (sibu_name is null) then 'ëççáåv'
/*END*/
       else rtrim(sibu_name) end as sibu_name,
/*IF !menuTotaledKbn.equals("TOTAL")*/
       campdata.menu_cd,
       rtrim(pc10.menu_name_kj) as menu_name_kj,
/*END*/
       taisho_tenpo_cnt,
       menu_uriage,
       kazu_kei,
       uriage,
       kyakusu,
       uriage_zen,
       kyakusu_zen
/*IF !menuTotaledKbn.equals("TOTAL")*/
,      case when sibu_name is null and pc10.menu_name_kj is null then '8_TOTAL' 
            when sibu_name is null and pc10.menu_name_kj is not null then '7_MENU_TOTAL' 
--ELSE
,      case when sibu_name is null then '8_TOTAL' 
/*END*/
            else '0' end as row_type
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
/*IF !menuTotaledKbn.equals("TOTAL")*/
       bd09.menu_cd,
/*END*/
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
       ,      bm10.sibu_name
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
         and    bn01.eigyo_dt between /*campFrom*/'20071201' and /*campTo*/'20071219'
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
/*IF shukeiKbn.equals("AREADAI")*/
       and   bm10.area_dai_flg = '1'
       and   bm10.sibu_cd = bm01.area_dai
--ELSE
       and   bm10.sibu_cd     = bm01.sibu_cd
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
       group by rollup ((bn01sub.company_cd, 
                         bm10.honbu_cd,
                         bm10.honbu_name,
                         bm10.jigyou_cd,
                         bm10.jigyou_name,
                         bm10.slarea_cd,
                         bm10.slarea_name,
                         bm10.sibu_cd, 
                         bm10.sibu_name))
     ) bn01 
     left join 
     (
       select bm01.company_cd
       ,      bm10.sibu_cd
/*IF !menuTotaledKbn.equals("TOTAL")*/
/*IF menuTotaledKbn.equals("SHUYAKU")*/
       ,      (case when bm69.sum_menu_cd is null then bd09.menu_cd else bm69.sum_menu_cd end) as menu_cd
--ELSE
       ,      bd09.menu_cd       
/*END*/
/*END*/
       ,      sum(bd09.uriage) as menu_uriage
/*IF !menuTotaledKbn.equals("TANPIN")*/       
       ,      sum(bd09.kazu_kei * (case when bm69.conv_value is null then 1 else bm69.conv_value end)) as kazu_kei
--ELSE
       ,      sum(bd09.kazu_kei) as kazu_kei
/*END*/       
       from   bd09cppr bd09,
              bm01tenm bm01,
              bm10gsib bm10,
              bm65cpms bm65,
              bm61cpmn bm61
/*IF !menuTotaledKbn.equals("TANPIN")*/
              left outer join bm69symp bm69 on (bm61.camp_id = bm69.camp_id and bm61.menu_cd = bm69.menu_cd)
/*END*/
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
       and   bd09.camp_id = bm61.camp_id
       and   bd09.menu_cd = bm61.menu_cd
/*IF !shukeiKbn.equals("AREADAI")*/
       and   bm01.sibu_cd = bm10.sibu_cd
--ELSE
       and   bm01.area_dai = bm10.sibu_cd
/*END*/
/*IF !menuTotaledKbn.equals("TOTAL")*/
/*IF menuTotaledKbn.equals("SHUYAKU")*/
       group by rollup ((bm01.company_cd,(case when bm69.sum_menu_cd is null then bd09.menu_cd else bm69.sum_menu_cd end)), (bm01.company_cd, bm10.sibu_cd, (case when bm69.sum_menu_cd is null then bd09.menu_cd else bm69.sum_menu_cd end)))
--ELSE
       group by rollup ((bm01.company_cd,bd09.menu_cd), (bm01.company_cd, bm10.sibu_cd, bd09.menu_cd))
/*END*/
--ELSE
       group by rollup ((bm01.company_cd, bm10.sibu_cd))
/*END*/
     ) bd09 on (bn01.sibu_cd = bd09.sibu_cd or (bn01.sibu_cd is null and bd09.sibu_cd is null))
) as campData
/*IF !menuTotaledKbn.equals("TOTAL")*/
left join pc10smnu pc10 on (campdata.menu_cd = pc10.menu_cd)
/*END*/
order by 
     company_cd
    ,sibu_cd
/*IF !menuTotaledKbn.equals("TOTAL")*/
    ,campdata.menu_cd
/*END*/