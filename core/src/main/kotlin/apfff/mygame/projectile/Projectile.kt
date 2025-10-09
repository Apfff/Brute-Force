package apfff.mygame.projectile

import apfff.mygame.projectile.components.BehaviorComponent
import apfff.mygame.projectile.components.PhysicsComponent
import apfff.mygame.projectile.components.orderPhysicsComponents
import com.badlogic.gdx.Gdx
import com.badlogic.gdx.graphics.Color
import com.badlogic.gdx.math.Vector2
import ktx.math.div

class Projectile (
  val pos: Vector2,
  var radius: Float,
  val mass: Float = 1f,
  val color: Color = Color.WHITE,
  val physicsComponents: ArrayList<PhysicsComponent> = ArrayList(),
  val behaviorComponents: ArrayList<BehaviorComponent> = ArrayList() //for like homing
) {

  val prevPos: Vector2 = pos.cpy()
  val velocity: Vector2 = Vector2()
  val baseRadius = radius
  var scale: Float = 1f

  init {
    orderPhysicsComponents(physicsComponents)
  }

  fun applyForce(force: Vector2){
    velocity.add(force/mass)
  }

  fun step(dt: Float){
    prevPos.set(pos)
    radius = baseRadius * scale
    for(pc in physicsComponents){
      pc.step(velocity, dt)
    }
    for(bc in behaviorComponents){
      bc.step(this, dt)
    }
    pos.apply {
      x += dt * velocity.x
      y += dt * velocity.y
    }
  }
}

