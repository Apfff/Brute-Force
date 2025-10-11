package apfff.mygame

import apfff.mygame.entities.impulse.Impulse
import com.badlogic.gdx.Gdx
import com.badlogic.gdx.graphics.Color
import com.badlogic.gdx.graphics.GL20
import com.badlogic.gdx.graphics.glutils.ShapeRenderer
import com.badlogic.gdx.math.MathUtils
import ktx.app.clearScreen
import ktx.assets.disposeSafely

class GraphicsEngine(
  val gameEngine: GameEngine
) {

  private val shapeRenderer = ShapeRenderer()

  fun draw(){
    val alpha = gameEngine.getAlpha()
    val projectiles = gameEngine.getProjectiles()
    val impulses = gameEngine.getImpulses()

    clearScreen(0f,0f,0f)
    Gdx.gl.glEnable(GL20.GL_BLEND)
    Gdx.gl.glBlendFunc(GL20.GL_SRC_ALPHA, GL20.GL_ONE_MINUS_SRC_ALPHA)
    shapeRenderer.begin(ShapeRenderer.ShapeType.Filled)
    for (projectile in projectiles) {
      shapeRenderer.color = projectile.color
      shapeRenderer.circle(
        MathUtils.lerp(projectile.prevPos.x, projectile.pos.x, alpha),
        MathUtils.lerp(projectile.prevPos.y, projectile.pos.y, alpha),
        projectile.radius
      )

    }
    for (impulse in impulses){
      if(impulse is Impulse){
        shapeRenderer.color = impulse.color.cpy().apply { a = 0.5f }
        shapeRenderer.circle(
          impulse.pos.x,
          impulse.pos.y,
          impulse.strength / 8
        )
      }
    }
    shapeRenderer.end()
    shapeRenderer.begin(ShapeRenderer.ShapeType.Line)
    shapeRenderer.color = Color.YELLOW
    for (impulse in impulses){
      if(impulse is Impulse){
        shapeRenderer.color = impulse.color
        shapeRenderer.circle(
          impulse.pos.x,
          impulse.pos.y,
          impulse.radius
        )
      }
    }
    shapeRenderer.end()
  }

  fun dispose() {
    shapeRenderer.disposeSafely()
  }
}