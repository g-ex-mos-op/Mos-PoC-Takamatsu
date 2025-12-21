select
  bm68.company_cd,
  bm68.mise_cd,
  bm68.meter_kbn,
  bm68.electric_flg,
  bm68.power_flg,
  bm68.gas_flg,
  bm68.water_flg,
  bm68.electric_flg as electric_flg_save,
  bm68.power_flg as power_flg_save,
  bm68.gas_flg as gas_flg_save,
  bm68.water_flg as water_flg_save,
  bm68.first_user,
  bm68.first_pgm,
  bm68.first_tmsp,
  bm68.last_user,
  bm68.last_pgm,
  bm68.last_tmsp,
  rtrim(bm10.sibu_name) as sibu_name,
  case when bm01.close_dt < /*sysDate*/'20080822' then concat(rtrim(replace(bm01.mise_name_kj, '@', '')), '(CLOSE)')
       else rtrim(replace(bm01.mise_name_kj, '@', ''))
  end  as mise_name_kj,
  case when bm68.electric_flg = '1' then 'TRUE' else 'FALSE' end as electric_flg_disp,
  case when bm68.power_flg = '1' then 'TRUE' else 'FALSE' end as power_flg_disp,
  case when bm68.gas_flg = '1' then 'TRUE' else 'FALSE' end as gas_flg_disp,
  case when bm68.water_flg = '1' then 'TRUE' else 'FALSE' end as water_flg_disp

from
  bm68mset bm68,
  bm01tenm bm01,
  bm10gsib bm10
where
    bm68.company_cd = /*companyCd*/'00'
and bm68.meter_kbn = /*meterKbn*/'0'
and bm68.company_cd = bm01.company_cd
and bm68.mise_cd = bm01.mise_cd
and bm01.company_cd = bm10.company_cd
and bm01.sibu_cd = bm10.sibu_cd
and bm01.sibu_cd = /*sibuCd*/'011'
order by bm68.mise_cd