/*
 * This program is free software: you can redistribute it and/or modify it under
 * the terms of the GNU General Public License as published by the Free Software
 * Foundation, either version 3 of the License, or (at your option) any later
 * version.
 *
 * This program is distributed in the hope that it will be useful, but WITHOUT
 * ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS
 * FOR A PARTICULAR PURPOSE. See the GNU General Public License for more
 * details.
 *
 * You should have received a copy of the GNU General Public License along with
 * this program. If not, see <http://www.gnu.org/licenses/>.
 */

package ru.catssoftware.gameserver.skills.effects;

import ru.catssoftware.gameserver.model.L2Effect;
import ru.catssoftware.gameserver.model.L2Skill;
import ru.catssoftware.gameserver.model.actor.instance.L2PcInstance;
import ru.catssoftware.gameserver.skills.Env;
import ru.catssoftware.gameserver.templates.skills.L2EffectType;

/**
 * @author Forsaiken, kombat
 */
public final class EffectRecoverForce extends L2Effect
{
	public EffectRecoverForce(Env env, EffectTemplate template)
	{
		super(env, template);
	}

	@Override
	public L2EffectType getEffectType()
	{
		return L2EffectType.BUFF;
	}

	@Override
	public boolean onActionTime()
	{
		if (getEffected() instanceof L2PcInstance)
		{
			L2PcInstance player = (L2PcInstance) getEffected();
			L2Skill chargeSkill = player.getChargeSkill();
			if (chargeSkill != null && player.getCharges() < chargeSkill.getMaxCharges())
				player.increaseCharges(1, chargeSkill.getMaxCharges());
		}
		return true;
	}
}
