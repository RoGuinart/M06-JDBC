CREATE OR REPLACE FUNCTION showEmptySports() RETURNS TABLE(Code INTEGER, Name VARCHAR)
AS $$
	select * from sports where code not in (
	    select distinct sport_code from athletes where sport_code is not null
    );
$$ LANGUAGE sql;
