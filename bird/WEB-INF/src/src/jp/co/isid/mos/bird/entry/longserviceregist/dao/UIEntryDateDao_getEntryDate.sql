SELECT
    entry_cd,
    entry_year,
    entry_kai,
    usertype_cd,
    day_kbn,
    from_dt,
    to_dt,
    first_user,
    first_pgm,
    first_tmsp,
    last_user,
    last_pgm,
    last_tmsp
FROM
    br18entd
WHERE
entry_cd = /*entryCd*/'20'
AND entry_year = /*entryYear*/'2006'
AND entry_kai = /*entryKai*/'001'
AND (
      (usertype_cd = '02' AND day_kbn = '04')
   OR (usertype_cd = '02' AND day_kbn = '03')
   OR (usertype_cd = '01' AND day_kbn = '04')
   OR (usertype_cd = '01' AND day_kbn = '03')
)
ORDER BY
usertype_cd DESC,
day_kbn DESC
