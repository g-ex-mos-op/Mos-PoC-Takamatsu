select 
  bm12.staff_id,
  bm12.company_cd,
  bm12.oner_cd,
  bm12.old_oner_cd,
  bm12.mise_cd_1,
  bm01.mise_name_kj as mise_cd_1_name,
  bm12.mise_cd_2,
  bm12.mise_cd_3,
  bm12.mise_cd_4,
  bm12.mise_cd_5,
  bm12.staff_l_name_kj,
  bm12.staff_f_name_kj,
  bm12.staff_l_name_kna,
  bm12.staff_f_name_kna,
  bm12.sex,
  bm12.birthday,
  bm12.job,
  bm12.situation_kbn,
  case when bm12.situation_kbn = '0' then 'Šˆ“®’†' 
       when bm12.situation_kbn = '1' then '‹xE’†'
  end as SITUATION_KBN_DISP,
  bm12.move_dt,
  bm12.retire_dt,
  bm12.leave_dt,
  bm12.return_dt,
  bm12.note,
  bm12.spare_flg1,
  bm12.spare_flg2,
  bm12.spare_flg3,
  bm12.first_user,
  bm12.first_pgm,
  bm12.first_tmsp,
  bm12.last_user,
  bm12.last_pgm,
  bm12.last_tmsp,
  bt32left.reentry_base_year,
  case when rtrim(bt32left.reentry_base_year) = '' then '1' else '0'
  end as reentry_flg
from
  bm01tenm bm01,
  bm12staf bm12

  left join (  
    select
        bt32m.reentry_base_year,
        bt32m.staff_id
    from
        bt32mlkr bt32m
    where
        concat(total_last_year ,total_last_kai ) = (
        
            select max(concat(total_last_year ,total_last_kai )) as total_last
              from bt32mlkr
             where cast(integer(reentry_base_year) + 1 as char(4)) >= /*entryYear*/'2006'
               and concat(total_last_year ,total_last_kai ) <> /*entryYearKai*/'2006003'
               and staff_id = bt32m.staff_id
             group by staff_id
        )
  ) bt32left on bt32left.staff_id = bm12.staff_id
,   (SELECT STAFF_ID 
     FROM BT30ENKJ 
     WHERE ENTRY_CD IN ('01', '05') 
     AND COURSE_STATUS = '1'
     GROUP BY STAFF_ID) BT31
where
  bm12.company_cd = /*companyCd*/'00' and
  bm01.oner_cd = /*onerCd*/'36478' and
  bm12.company_cd = bm01.company_cd and
  bm12.mise_cd_1 = bm01.mise_cd and 
  bm12.situation_kbn in ('0', '1') and

not exists (

    select
        bt32_1.staff_id
    from
    (
        select
            bt32m.staff_id,
            bt32m.sub1_result,
            bt32m.sub2_result,
            bt32m.sub3_result
        from
            bt32mlkr bt32m
        where
            concat(total_last_year ,total_last_kai ) = (
            
                select max(concat(total_last_year ,total_last_kai )) as total_last
                  from bt32mlkr
                 where cast(integer(reentry_base_year) + 1 as char(4)) >= /*entryYear*/'2007'
                   and concat(total_last_year ,total_last_kai ) <> /*entryYearKai*/'2007001'
                   and staff_id = bt32m.staff_id
                 group by staff_id
            )

    ) bt32_1
    inner join
    (
        select
            bt32m.staff_id,
            bt32m.sub3_result
        from
            bt32mlkr bt32m
        where
            concat(sub3_last_year ,sub3_last_kai) = (

                select
                    max(concat(entry_year, entry_kai))
                from
                    br17entl
                where entry_cd = '10'
                  and concat(entry_year, entry_kai) < /*entryYearKai*/'2007001'
                  and staff_id = bt32m.staff_id
                group by staff_id

            )

    ) bt32_2 on bt32_1.staff_id = bt32_2.staff_id
    where (((bt32_1.sub1_result = '1') and (bt32_1.sub2_result = '1') and (bt32_1.sub3_result = '1'))
       or ((bt32_1.sub1_result = '1') and (bt32_1.sub2_result = '1') and (bt32_1.sub3_result = '0'))
       or ((bt32_1.sub1_result = '1') and (bt32_1.sub2_result = '2') and (bt32_1.sub3_result = '0'))
       or ((bt32_1.sub1_result = '2') and (bt32_1.sub2_result = '1') and (bt32_1.sub3_result = '0'))
       or ((bt32_1.sub1_result = '2') and (bt32_1.sub2_result = '2') and (bt32_1.sub3_result = '0')))
      and bt32_1.staff_id = bm12.staff_id
      and bt32_2.staff_id = bm12.staff_id
) and
(
    not exists (
        select
            *
        from
            bt26upjk
        where
            staff_id = bm12.staff_id and
            expire_flg != '1'
    ) or
    
    not exists (
        select
            *
        from
            bt32mlkr
        where
            staff_id = bm12.staff_id and
            (total_result = '3' or total_result = '4')
    )
)
AND bm12.STAFF_ID = BT31.STAFF_ID
and not exists (
        select * from bt26upjk
        where staff_id = bm12.staff_id
        and   expire_flg = '0'
    ) 


order by 
  bm12.mise_cd_1, reentry_flg, reentry_base_year desc, hex(  bm12.staff_l_name_kna || bm12.staff_f_name_kna)
  