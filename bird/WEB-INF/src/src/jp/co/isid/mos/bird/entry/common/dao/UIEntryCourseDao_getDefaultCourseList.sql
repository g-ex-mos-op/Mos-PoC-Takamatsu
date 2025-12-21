select
    BC11.ENTRY_CD,
    rtrim(BC11.COURSE_CD) as COURSE_CD,
    rtrim(BC11.COURSE_NAME) as COURSE_NAME
from
    BC11DEFC as BC11
where
    BC11.ENTRY_CD = /*entryCd*/'10'
order by
    BC11.COURSE_CD
