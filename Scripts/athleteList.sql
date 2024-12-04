CREATE OR REPLACE FUNCTION athleteList() 
RETURNS TABLE ("Athlete name" varchar, "Athlete code" int, "Sport name" varchar)
AS $$
	BEGIN
		RETURN QUERY
			SELECT athletes.name "Athlete name", athletes.code "Athlete code", sports.name "Sport name"
			FROM athletes left outer JOIN sports on sports.code = sports.sport_code;
		
	EXCEPTION
		WHEN NO_DATA_FOUND 	THEN RAISE EXCEPTION 'No athletes';
		WHEN TOO_MANY_ROWS	THEN RAISE EXCEPTION 'Referential integrity error';
	--	WHEN 		OTHERS 	THEN RAISE EXCEPTION 'Unknown error.';
		
	END;
$$ LANGUAGE plpgsql;
