select 
  company_cd,
  mise_cd,
  rtrim(mise_name_kj) as mise_name_kj,
  sibu_cd,
  rtrim(sibu_name) as sibu_name,
  case when hanbai_kin is null then decimal(0) else hanbai_kin end as hanbai_kin,
  case when hanbai_kosu is null then decimal(0) else hanbai_kosu end as hanbai_kosu,
  case when kingaku_kosei_hi is null then decimal(0.00) else kingaku_kosei_hi end as kingaku_kosei_hi,
  uriage,
  zen_uriage,
  zennen_hi,
  rank
from 
(
select
  uriageTbl.company_cd,
  uriageTbl.mise_cd,
  bm01.mise_name_kj,
  bm01.sibu_cd,
  bm10.sibu_name,
  case when (campTbl_from.hanbai_kin is null and campTbl_to.hanbai_kin is null) then 0
       when (campTbl_from.hanbai_kin is null) then campTbl_to.hanbai_kin
       else (campTbl_to.hanbai_kin - campTbl_from.hanbai_kin) 
  end as hanbai_kin,
  case when (campTbl_from.hanbai_kosu is null and campTbl_to.hanbai_kosu is null) then 0
       when (campTbl_from.hanbai_kosu is null) then campTbl_to.hanbai_kosu 
       else (campTbl_to.hanbai_kosu - campTbl_from.hanbai_kosu ) 
  end as hanbai_kosu,
  uriageTbl.uriage,
  uriageTbl.uriage_zen_doyo as zen_uriage,
  
  case when uriageTbl.uriage = 0 then decimal(0.00)
       when (case when campTbl_to.hanbai_kin is null then 0
                  else (campTbl_to.hanbai_kin - (case when campTbl_from.hanbai_kin is null then 0 else campTbl_from.hanbai_kin end))
             end) = 0  then decimal(0.00)
       when (campTbl_to.hanbai_kin - (case when campTbl_from.hanbai_kin is null then 0 else campTbl_from.hanbai_kin end)) < 0
	 then DECIMAL((DOUBLE(campTbl_to.hanbai_kin - (case when campTbl_from.hanbai_kin is null then 0 else campTbl_from.hanbai_kin end)) / DOUBLE(uriageTbl.uriage)*100)-0.005, 11, 2)
	 else DECIMAL((DOUBLE(campTbl_to.hanbai_kin - (case when campTbl_from.hanbai_kin is null then 0 else campTbl_from.hanbai_kin end)) / DOUBLE(uriageTbl.uriage)*100)+0.005, 11, 2) 
  end as kingaku_kosei_hi,

  case when uriageTbl.uriage_zen_doyo = 0 or (uriageTbl.uriage is null or uriageTbl.uriage = 0)
  then decimal(0.00)
  else DECIMAL((DOUBLE(uriageTbl.uriage)/DOUBLE(uriageTbl.uriage_zen_doyo)*100)+0.005, 11, 2) 
  end as zennen_hi,
/*IF rankMode == "1"*/
  rank() over(order by 
  case when uriageTbl.uriage = 0 then decimal(0.00)
       when (case when campTbl_to.hanbai_kin is null then 0
                  else (campTbl_to.hanbai_kin - (case when campTbl_from.hanbai_kin is null then 0 else campTbl_from.hanbai_kin end))
             end) = 0  then decimal(0.00)
       when (campTbl_to.hanbai_kin - (case when campTbl_from.hanbai_kin is null then 0 else campTbl_from.hanbai_kin end)) < 0
	 then DECIMAL(((DOUBLE(campTbl_to.hanbai_kin - (case when campTbl_from.hanbai_kin is null then 0 else campTbl_from.hanbai_kin end)) / DOUBLE(uriageTbl.uriage)*100)-0.005)*-1, 11, 2)
	 else DECIMAL((DOUBLE(campTbl_to.hanbai_kin - (case when campTbl_from.hanbai_kin is null then 0 else campTbl_from.hanbai_kin end)) / DOUBLE(uriageTbl.uriage)*100)+0.005, 11, 2) 
  end desc) as rank
/*END*/
/*IF rankMode == "2"*/
  rank() over(order by 
	  case when campTbl_to.hanbai_kosu is null
	  then 0
	  else (case when (campTbl_from.hanbai_kosu is null and campTbl_to.hanbai_kosu is null) then 0 when (campTbl_from.hanbai_kosu is null) then campTbl_to.hanbai_kosu else (campTbl_to.hanbai_kosu - campTbl_from.hanbai_kosu ) end) 
	  end
  desc) as rank
/*END*/
/*IF rankMode == "3"*/
  rank() over(order by 
	  case when uriageTbl.uriage_zen_doyo = 0
	  then 0
	  else DECIMAL((DOUBLE(uriageTbl.uriage)/DOUBLE(uriageTbl.uriage_zen_doyo)*100)+0.005, 11, 2) end
  desc) as rank
/*END*/
from
(
select
  bt60.company_cd,
  bt60.mise_cd,
  sum(uriage) as uriage,
  sum(uriage_zen_doyo) as uriage_zen_doyo
from 
  bt60znip bt60,
  bm65cpms bm65
where
    eigyo_dt between /*kikanFrom*/'20080602' and /*kikanTo*/'20080602'
and bm65.camp_id = /*campId*/'200808'
and bt60.company_cd = bm65.company_cd
and bt60.mise_cd = bm65.mise_cd
and bt60.company_cd=/*companyCd*/'00'
and bt60.open_kbn = 1
and bt60.oldm_flg <> '1'
group by
  bt60.company_cd,
  bt60.mise_cd

) uriageTbl
left join 
(
    select 
      bd03.company_cd,
      bd03.mise_cd,
      sum(bd03.uriage_rui) as hanbai_kin,
      sum(bd03.kazu_kei_rui * (case when menuMst.conv_value is null then 1 else menuMst.conv_value end)) as hanbai_kosu
    from 
      bd03cpml bd03,
      bm60cpdt bm60,
      bm65cpms bm65,
     (select 
        bm61.menu_cd,
        bm62.conv_value,
        bm62.sum_menu_cd
      from  
        bm61cpmn bm61 
        left join bm62symm bm62 on (bm61.menu_cd = bm62.menu_cd)
      where 
          bm61.camp_id = /*campId*/'200806'
     ) as menuMst
    where
        bm60.camp_id = /*campId*/'200806'
    and bd03.company_cd = bm60.company_cd
    and bd03.menu_cd = menuMst.menu_cd
    and bd03.menu_dt = /*kikanFromZen*/'20080101' 
    and bm65.camp_id = bm60.camp_id
    and bm65.company_cd = bd03.company_cd
    and bm65.mise_cd = bd03.mise_cd
    group by
      bd03.company_cd,
      bd03.mise_cd
) campTbl_from 
on (campTbl_from.company_cd = uriageTbl.company_cd and
    campTbl_from.mise_cd = uriageTbl.mise_cd) 
left join 
(
    select 
      bd03.company_cd,
      bd03.mise_cd,
      sum(bd03.uriage_rui) as hanbai_kin,
      sum(bd03.kazu_kei_rui * (case when menuMst.conv_value is null then 1 else menuMst.conv_value end)) as hanbai_kosu
    from 
      bd03cpml bd03,
      bm60cpdt bm60,
      bm65cpms bm65,
     (select 
        bm61.menu_cd,
        bm62.conv_value
      from 
        bm61cpmn bm61
        left join bm62symm bm62 on (bm61.menu_cd = bm62.menu_cd) 
      where 
         bm61.camp_id = /*campId*/'200806'
     ) as menuMst
    where
        bm60.camp_id = /*campId*/'200806'
    and bd03.company_cd = bm60.company_cd
    and bd03.menu_cd = menuMst.menu_cd
    and bd03.menu_dt = /*kikanTo*/'20080101' 
    and bm65.camp_id = bm60.camp_id
    and bm65.company_cd = bd03.company_cd
    and bm65.mise_cd = bd03.mise_cd
    group by
      bd03.company_cd,
      bd03.mise_cd
) campTbl_to 
on (campTbl_to.company_cd = uriageTbl.company_cd and
    campTbl_to.mise_cd = uriageTbl.mise_cd),
bm01tenm bm01,
bm10gsib bm10

where 
    uriageTbl.company_cd = bm01.company_cd
and uriageTbl.mise_cd = bm01.mise_cd
and bm01.company_cd = bm10.company_cd
and bm01.sibu_cd = bm10.sibu_cd
) as dataTbl
where dataTbl.rank <= 100
order by rank, sibu_cd, mise_cd