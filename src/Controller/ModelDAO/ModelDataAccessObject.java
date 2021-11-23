/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package Controller.ModelDAO;

import java.util.List;

/**
 *
 * @author LENOVO
 * @param <EntityType>
 * @param <Key>
 */
 public abstract class ModelDataAccessObject<EntityType, Key>{
    abstract public void insert(EntityType entity);
    abstract public void update(EntityType entity);
    abstract public void delete(Key ID);
    abstract public List<EntityType> selectAll();
    abstract public EntityType selectByID(Key ID);
    abstract protected List<EntityType> selectBySQL(String sql, Object...args);
}
