This is a model of dates, times and durations from the ISO8601 standard. It is 
intended as a core library for use  by higher level models that require dates and/or 
times and/or durations. Dates are  based on the Gregorian calendar. The Gregorian 
calendar commenced in October 1582, but it is extended backwards to year 1 in the 
proleptic Gregorian calendar, as per ISO 8601.
Times assume Co-ordinated Univeral Time (UTC). Timezones and daylight savings are 
not supported. The granularity of times is to the nearest millisecond.
A duration is modelled as a number of elapsed milliseconds (being the smallest unit 
of time). All functions are explicit and executable. Where a non-executable condition 
adds value, it is included as a comment.


#******************************************************
#  AUTOMATED TEST SETTINGS
#------------------------------------------------------
#AUTHOR= Paul Chisholm
#LANGUAGE_VERSION=vdm10
#INV_CHECKS=true
#POST_CHECKS=true
#PRE_CHECKS=true
#DYNAMIC_TYPE_CHECKS=true
#SUPPRESS_WARNINGS=false
#ENTRY_POINT=Set`sum({1,2,3,4,5,6,7,8,9})
#EXPECTED_RESULT=NO_ERROR_TYPE_CHECK
#******************************************************
