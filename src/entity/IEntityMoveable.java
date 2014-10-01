package entity;

import map.IMap;


public interface IEntityMoveable extends IEntity {
	public void move(int Delta, IMap map);

}
