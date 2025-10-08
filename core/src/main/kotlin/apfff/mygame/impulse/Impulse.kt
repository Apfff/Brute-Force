package apfff.mygame.impulse

import com.badlogic.gdx.math.Vector2

interface Impulse {
  fun computeForceAt(pos: Vector2): Vector2
}