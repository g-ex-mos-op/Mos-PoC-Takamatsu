select
    count(staff_id) cnt
from
    bt32mlkr
where
    staff_id = /*staffid*/'98005269' and
    rtrim(exam_no) <> ''
