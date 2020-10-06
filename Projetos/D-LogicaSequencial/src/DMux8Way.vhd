library IEEE;
use IEEE.STD_LOGIC_1164.ALL;

entity DMux8Way is
	port ( 
			a:   in  STD_LOGIC;
			sel: in  STD_LOGIC_VECTOR(2 downto 0);
			q0:  out STD_LOGIC;
			q1:  out STD_LOGIC;
			q2:  out STD_LOGIC;
			q3:  out STD_LOGIC;
			q4:  out STD_LOGIC;
			q5:  out STD_LOGIC;
			q6:  out STD_LOGIC;
			q7:  out STD_LOGIC);
end entity;

architecture arch of DMux8Way is
begin

q0 <= not sel(0) and not sel(1) and not sel(2) and a;

q1 <= sel(0) and not sel(1) and not sel(2) and a;

q2 <= not sel(0) and sel(1) and not sel(2) and a;

q3 <= sel(0) and sel(1) and not sel(2) and  a;

q4 <= not sel(0) and not sel(1) and sel(2) and a;

q5 <= sel(0) and not sel(1) and sel(2) and a;

q6 <= not sel(0) and sel(1) and sel(2) and a;

q7 <= sel(0) and sel(1) and sel(2) and  a;
	

end architecture;
