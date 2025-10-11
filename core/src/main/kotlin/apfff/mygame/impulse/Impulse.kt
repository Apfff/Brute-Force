package apfff.mygame.impulse

import apfff.mygame.PhysicEntity
import com.badlogic.gdx.math.Vector2

interface Impulse: PhysicEntity {
  fun computeForceAt(pos: Vector2): Vector2
}