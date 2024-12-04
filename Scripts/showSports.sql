CREATE OR REPLACE FUNCTION showSports() 
RETURNS TABLE(Code INTEGER, Name VARCHAR)
AS $$
	select * from sports;
$$ LANGUAGE sql;
