SELECT
    entry_cd,
    entry_year,
    entry_kai,
    selection_kbn,
    selection_index,
    selection_name,
    first_user,
    first_pgm,
    first_tmsp,
    last_user,
    last_pgm,
    last_tmsp
FROM
    br53ents
WHERE
    entry_cd =/*entryCd*/'35'
AND entry_year=/*entryYear*/'2006'
AND entry_kai=/*entryKai*/'001'
ORDER BY
    selection_kbn,
    selection_index