select 
       row_number() over (partition by company_cd, mise_cd) as mise_group_seq,
       company_cd,
       mise_cd,
       block_cd,
       kbn1 as TENPO_SHUBETU,
/*IF userTypeCd == "01" && shukeiKbn.equals("SIBU")*/
       case when (company_cd is null and mise_cd is null ) then 'ëççáåv'
            else rtrim(block_name) end as block_name,
       rtrim(mise_name_kj) as mise_name_kj,
--ELSE
       rtrim(block_name) as block_name,
       case when (mise_cd is null /*IF !menuTotaledKbn.equals("TOTAL")*/ and campData.menu_cd is null/*END*/) then 'ëççáåv'
            else rtrim(mise_name_kj) end as mise_name_kj,
/*END*/
/*IF !menuTotaledKbn.equals("TOTAL")*/
       campData.menu_cd,
       rtrim(pc10.menu_name_kj) as menu_name_kj,
/*END*/
       sum(menu_uriage) as menu_uriage,
       sum(kazu_kei) as kazu_kei,
       sum(uriage) as uriage,
       sum(kyakusu) as kyakusu,
       sum(uriage_zen) as uriage_zen,
       sum(kyakusu_zen) as kyakusu_zen,
       eigyo_days,
       eigyo_days_zen,
       RANK_IN_SIBU,
       RANK_IN_ALL,
/*IF menuTotaledKbn.equals("TOTAL")*/
       case when (company_cd is null) then '8_TOTAL' 
            when (mise_cd is null and block_cd is not null) then '1_BLOCK_TOTAL'
--ELSE
       case when (menu_name_kj is null and mise_cd is null) then '8_TOTAL' 
            when (mise_cd is null and company_cd is not null) then '7_MENU_TOTAL'
/*END*/
            else '0' end as row_type,
       COUNT(DISTINCT (CASE WHEN OPEN_KBN = 1 THEN MISE_CD ELSE null END)) as taisho_tenpo_cnt,
       sibu_tenpo_cnt,
       ALL_TENPO_CNT
from
(
select bn01.company_cd,
       bn01.mise_cd,
       bn01.mise_kbn,
       bn01.mise_name_kj,
       bn01.kbn1,
       bn01.sibu_cd,
       bn01.block_cd,
       bn01.block_name,
/*IF !menuTotaledKbn.equals("TOTAL")*/
       bd09.menu_cd,
/*END*/
       bd09.menu_uriage,
       bd09.kazu_kei,
       bn01.uriage,
       bn01.kyakusu,
       bn01.uriage_zen,
       bn01.kyakusu_zen,
       bn01.eigyo_days,
       bn01.eigyo_days_zen,
       bn01.open_kbn,
/*IF rankKind.equals("KINGAKU_KOSEIHI")*/
       RANK() OVER(partition by bn01.company_cd /*IF !menuTotaledKbn.equals("TOTAL")*/ ,bd09.menu_cd /*END*/
                order by DECIMAL((CASE WHEN bn01.uriage = 0 or (bd09.menu_uriage is null or bd09.menu_uriage = 0)
                                       THEN 0.00
                                       WHEN ((DOUBLE(bd09.menu_uriage)/DOUBLE(bn01.uriage)*100)) < 0
                                       THEN ((DOUBLE(bd09.menu_uriage)/DOUBLE(bn01.uriage)*100)-0.005) * -1
                                       ELSE ((DOUBLE(bd09.menu_uriage)/DOUBLE(bn01.uriage)*100)+0.005) 
                                       END), 20, 2) desc) AS RANK_IN_ALL,
       RANK() OVER(partition by bn01.company_cd, bn01.sibu_cd /*IF !menuTotaledKbn.equals("TOTAL")*/ ,bd09.menu_cd /*END*/
                order by DECIMAL((CASE WHEN bn01.uriage = 0 or (bd09.menu_uriage is null or bd09.menu_uriage = 0)
                                       THEN 0.00
                                       WHEN ((DOUBLE(bd09.menu_uriage)/DOUBLE(bn01.uriage)*100)) < 0
                                       THEN ((DOUBLE(bd09.menu_uriage)/DOUBLE(bn01.uriage)*100)-0.005) * -1
                                       ELSE ((DOUBLE(bd09.menu_uriage)/DOUBLE(bn01.uriage)*100)+0.005) 
                                       END), 20, 2) desc) AS RANK_IN_SIBU,
--ELSE
       RANK() OVER(partition by bn01.company_cd /*IF !menuTotaledKbn.equals("TOTAL")*/ ,bd09.menu_cd /*END*/
                order by sum(bd09.kazu_kei) desc) AS RANK_IN_ALL,
       RANK() OVER(partition by bn01.company_cd, bn01.sibu_cd /*IF !menuTotaledKbn.equals("TOTAL")*/ ,bd09.menu_cd /*END*/
                order by sum(bd09.kazu_kei) desc) AS RANK_IN_SIBU,
/*END*/
       bn01.sibu_tenpo_cnt,
       bn01.ALL_TENPO_CNT
from  
     (
       select bn01sub.company_cd
       ,      bn01sub.mise_cd
       ,      bm01.mise_name_kj
       ,      substr(bm01.mise_kbn, 2, 1) as mise_kbn
       ,      bn01sub.kbn1
       ,      bm01.block_cd
       ,      bc23.block_name
/*IF shukeiKbn.equals("AREADAI")*/
       ,      bm01.area_dai as sibu_cd
--ELSE
       ,      bm01.sibu_cd
/*END*/
       ,      bn01sub.open_kbn
       ,      sum(bn01sub.uriage) as uriage
       ,      sum(bn01sub.kyakusu) as kyakusu
       ,      sum(bn01sub.uriage_zen) as uriage_zen
       ,      sum(bn01sub.kyakusu_zen) as kyakusu_zen
       ,      sum(bn01sub.eigyo_days) as eigyo_days
       ,      sum(bn01sub.eigyo_days_zen) as eigyo_days_zen
       ,      count(*) over() as all_tenpo_cnt
/*IF shukeiKbn.equals("AREADAI")*/
       ,      count(bn01sub.mise_cd) over(partition by bn01sub.company_cd, bm01.area_dai) as sibu_tenpo_cnt
--ELSE
       ,      count(bn01sub.mise_cd) over(partition by bn01sub.company_cd, bm01.sibu_cd) as sibu_tenpo_cnt
/*END*/
       from 
       (    
         select bn01.company_cd
         ,      bn01.mise_cd
         ,      max(bn01.kbn1) as kbn1
         ,      sum(bt60.uriage) as uriage
         ,      sum(bt60.kyakusu) as kyakusu
         ,      sum(bt60.uriage_zen_doyo) as uriage_zen
         ,      sum(bt60.kyakusu_zen_doyo) as kyakusu_zen
         ,      sum(bt60.eigyo_days) eigyo_days
         ,      sum(bt60.eigyo_days_zen_doyo) eigyo_days_zen
         ,      max(bt60.open_kbn) as open_kbn
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
       ,    bc23blck bc23
       where bm65.camp_id = /*campId*/'200832'
       and   bm65.company_cd = bn01sub.company_cd
       and   bm65.mise_cd = bn01sub.mise_cd
/*IF userTypeCd == "01" && limitFlg == true */
       and  bm01.sibu_cd in (
            select   bm51.sibu_cd
            from     bm51svsb bm51
            where  bm51.company_cd = /*companycd*/'00'
            and    bm51.sv_cd      = /*userid*/'99990005'
            group by bm51.sibu_cd
       )
/*END*/
       and   bm65.company_cd = bm01.company_cd
       and   bm65.mise_cd = bm01.mise_cd
       and   bm01.block_cd = bc23.block_cd
       group by bn01sub.company_cd
       ,      bn01sub.mise_cd
       ,      substr(bm01.mise_kbn, 2, 1)
       ,      bm01.mise_name_kj
       ,      bn01sub.kbn1
/*IF shukeiKbn.equals("AREADAI")*/
       ,      bm01.area_dai
--ELSE
       ,      bm01.sibu_cd
/*END*/
       ,      bn01sub.open_kbn
       ,      bm01.block_cd
       ,      bc23.block_name
     ) bn01 
     left join 
     (
       select bm01.company_cd
       ,      bd09.mise_cd
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
       and   bd09.camp_id = bm61.camp_id
       and   bd09.menu_cd = bm61.menu_cd
       group by bm01.company_cd
       ,      bm01.sibu_cd
       ,      bd09.mise_cd
/*IF !menuTotaledKbn.equals("TOTAL")*/
/*IF menuTotaledKbn.equals("SHUYAKU")*/
       ,      (case when bm69.sum_menu_cd is null then bd09.menu_cd else bm69.sum_menu_cd end)
--ELSE
       ,      bd09.menu_cd       
/*END*/
/*END*/
     ) bd09 on (bn01.mise_cd = bd09.mise_cd)
group by 
       bn01.company_cd,
       bn01.mise_cd,
       bn01.mise_kbn,
       bn01.mise_name_kj,
       bn01.kbn1,
       bn01.sibu_cd,
       bn01.block_cd,
       bn01.block_name,
/*IF !menuTotaledKbn.equals("TOTAL")*/
       bd09.menu_cd,
/*END*/
       bd09.menu_uriage,
       bd09.kazu_kei,
       bn01.uriage,
       bn01.kyakusu,
       bn01.uriage_zen,
       bn01.kyakusu_zen,
       bn01.eigyo_days,
       bn01.eigyo_days_zen,
       bn01.open_kbn,
       bn01.all_tenpo_cnt,
       bn01.sibu_tenpo_cnt
) as campData
/*IF !menuTotaledKbn.equals("TOTAL")*/
,    pc10smnu pc10
/*END*/
where 
/*IF userTypeCd == "01" */
      sibu_cd = /*hyojiTaisho*/'011'
/*END*/
/*IF userTypeCd == "02" */
     mise_cd in (select mise_cd 
                 from bm01tenm 
                 where company_cd = /*companyCd*/'00' 
                 and   oner_cd in (select oner_cd from bm06uonr 
                                   where  USER_ID = /*userId*/'99990002'   
                                   AND   COMPANY_CD = bm01tenm.company_cd)
                )
/*END*/
/*IF userTypeCd == "03" */
     mise_cd in (select mise_cd 
                 from bm07uten 
                 where USER_ID = /*userId*/'99990003' and COMPANY_CD = /*companyCd*/'00'
                )
/*END*/
/*IF !menuTotaledKbn.equals("TOTAL")*/
and   campData.menu_cd = pc10.menu_cd
/*END*/
group by rollup(
/*IF userTypeCd == "01" && shukeiKbn.equals("SIBU") && menuTotaledKbn.equals("TOTAL")*/
      (company_cd,
       block_cd,
       block_name),
/*END*/
/*IF !menuTotaledKbn.equals("TOTAL")*/
      (company_cd,
       campData.menu_cd,
       pc10.menu_name_kj),
/*END*/
	  (company_cd, 
       mise_cd,
       mise_kbn,
       mise_name_kj,
       kbn1,
       block_cd,
       block_name,
/*IF !menuTotaledKbn.equals("TOTAL")*/
       campData.menu_cd,
       pc10.menu_name_kj,
/*END*/
       eigyo_days,
       eigyo_days_zen,
       RANK_IN_SIBU,
       RANK_IN_ALL,
       sibu_tenpo_cnt,
       ALL_TENPO_CNT)
)
order by 
/*IF menuTotaledKbn.equals("TOTAL")*/
/*IF userTypeCd == "01" && !shukeiKbn.equals("SIBU")*/
         mise_kbn,
/*END*/
/*IF userTypeCd == "01" && shukeiKbn.equals("SIBU")*/
         block_cd,
/*END*/
         mise_cd
--ELSE
         company_cd, 
/*IF userTypeCd == "01" && !shukeiKbn.equals("SIBU")*/
         mise_kbn,
/*END*/
/*IF userTypeCd == "01" && shukeiKbn.equals("SIBU")*/
         block_cd,
/*END*/
         mise_cd, 
         menu_cd
/*END*/