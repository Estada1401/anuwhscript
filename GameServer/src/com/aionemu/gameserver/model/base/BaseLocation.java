/*
 * This file is part of Encom. **ENCOM FUCK OTHER SVN**
 *
 *  Encom is free software: you can redistribute it and/or modify
 *  it under the terms of the GNU Lesser Public License as published by
 *  the Free Software Foundation, either version 3 of the License, or
 *  (at your option) any later version.
 *
 *  Encom is distributed in the hope that it will be useful,
 *  but WITHOUT ANY WARRANTY; without even the implied warranty of
 *  MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *  GNU Lesser Public License for more details.
 *
 *  You should have received a copy of the GNU Lesser Public License
 *  along with Encom.  If not, see <http://www.gnu.org/licenses/>.
 */
package com.aionemu.gameserver.model.base;

import java.util.*;

import com.aionemu.gameserver.model.Race;
import com.aionemu.gameserver.model.templates.base.BaseTemplate;

/**
 * @author Rinzler
 */

public class BaseLocation
{
	protected BaseTemplate template;
	protected Race race = Race.NPC;
	
	public BaseLocation() {
	}
	
	public BaseLocation(BaseTemplate template) {
		this.template = template;
	}
	
	public int getId() {
		return template.getId();
	}
	
	public int getWorldId() {
		return template.getWorldId();
	}
	
	public String getName() {
		return template.getName();
	}
	
	public Race getRace() {
		return race;
	}
	
	public void setRace(Race race) {
		this.race = race;
	}
}