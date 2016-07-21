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
package ru.catssoftware.gameserver.network.serverpackets;

import ru.catssoftware.gameserver.model.L2ItemInstance;
import ru.catssoftware.gameserver.model.L2Object;
import ru.catssoftware.gameserver.model.actor.instance.L2PcInstance;
import ru.catssoftware.gameserver.network.L2GameClient;

/**
 * 15
 * ee cc 11 43 		object id
 * 39 00 00 00 		item id
 * 8f 14 00 00 		x
 * b7 f1 00 00 		y
 * 60 f2 ff ff 		z
 * 01 00 00 00 		show item count
 * 7a 00 00 00      count                                         .
 *
 * format  dddddddd
 *
 * @version $Revision: 1.3.2.1.2.3 $ $Date: 2005/03/27 15:29:39 $
 */
public final class SpawnItem extends L2GameServerPacket
{
	private static final String	_S__15_SPAWNITEM	= "[S] 15 SpawnItem";
	private int					_objectId;
	private int					_itemId;
	private int					_x, _y, _z;
	private int					_stackable, _count;

	public SpawnItem(L2Object obj)
	{
		_objectId = obj.getObjectId();
		_x = obj.getX();
		_y = obj.getY();
		_z = obj.getZ();

		if (obj instanceof L2ItemInstance)
		{
			L2ItemInstance item = (L2ItemInstance) obj;
			_itemId = item.getItemDisplayId();
			_stackable = item.isStackable() ? 0x01 : 0x00;
			_count = item.getCount();
		}
		else
		{
			_itemId = obj.getPoly().getPolyId();
			_stackable = 0;
			_count = 1;
		}
	}

	@Override
	protected final void writeImpl(L2GameClient client,L2PcInstance activeChar)
	{
		writeC(0x0B);
		writeD(_objectId);
		writeD(_itemId);

		writeD(_x);
		writeD(_y);
		writeD(_z);
		// only show item count if it is a stackable item
		writeD(_stackable);
		writeD(_count);
		writeD(0x00); //c2
	}

	/* (non-Javadoc)
	 * @see ru.catssoftware.gameserver.serverpackets.ServerBasePacket#getType()
	 */
	@Override
	public String getType()
	{
		return _S__15_SPAWNITEM;
	}
}