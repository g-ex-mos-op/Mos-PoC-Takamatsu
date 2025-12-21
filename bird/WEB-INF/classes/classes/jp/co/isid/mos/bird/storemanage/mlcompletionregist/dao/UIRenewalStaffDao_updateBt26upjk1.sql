update BT26UPJK

set RENEW1_ENT_YEAR  = /*entity.entryYear*/'' 
,   RENEW1_ENT_KAI  = /*entity.entryKai*/'' 
,   RENEW1_COURSE  = /*entity.compleCourseCd*/''
,   RENEW1_COURSE_NAME  = /*entity.compleCourseName*/''
,   RENEW1_DATE  = /*entity.compleCourseDt*/''
,   RENEW1_STATUS  = /*entity.courseStatus*/''
,   LAST_USER  = /*entity.lastUser*/''
,   LAST_PGM  = /*entity.lastPgm*/'BSM006'
,   LAST_TMSP  = /*entity.lastTmsp*/

where STAFF_ID = /*entity.staffId*/''
and   LICENSE_DT = /*entity.licenseDt*/''
