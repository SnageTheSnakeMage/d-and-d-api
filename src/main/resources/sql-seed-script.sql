insert into monster ("id", name, size, type, armorClass, hitPoints, speeds, strength, dexterity, constitution, intelligence, wisdom, charisma, xpReward, actions) 
values (100, 'Skeleton', 'Medium', 'Undead',
 13, 13, '"speed", 30', 
 10, 14, 15, 6, 8, 5, 50, 
 '{\"Shortsword. Melee Weapon Attack: +4 to hit, reach 5 ft., one target. Hit: 5 (1d6 + 2) piercing damage.\" , 
\"Shortbow. Ranged Weapon Attack: +4 to hit, range 80/320 ft., one target. Hit: 5 (1d6 + 2) piercing damage.\"}');

insert into monster ("id", name, size, type, armorClass, hitPoints, speeds, strength, dexterity, constitution, intelligence, wisdom, charisma, xpReward, actions) 
values (101, 'Goblin', 'Small', 'Goblinoid',
 12, 10, '"speed", 30',
  11, 12, 10, 10, 11, 9, 8, 25,
   '{\"Dagger. Melee Weapon Attack: +5 to hit, reach 5 ft., one target. Hit: 4 (1d4 + 2) piercing damage.\"}');

