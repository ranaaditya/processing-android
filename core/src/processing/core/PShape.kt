/* -*- mode: java; c-basic-offset: 2; indent-tabs-mode: nil -*- */

/*
  Part of the Processing project - http://processing.org

  Copyright (c) 2012-19 The Processing Foundation
  Copyright (c) 2006-12 Ben Fry and Casey Reas

  This library is free software; you can redistribute it and/or
  modify it under the terms of the GNU Lesser General Public
  License version 2.1 as published by the Free Software Foundation.

  This library is distributed in the hope that it will be useful,
  but WITHOUT ANY WARRANTY; without even the implied warranty of
  MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the GNU
  Lesser General Public License for more details.

  You should have received a copy of the GNU Lesser General
  Public License along with this library; if not, write to the
  Free Software Foundation, Inc., 59 Temple Place, Suite 330,
  Boston, MA  02111-1307  USA
*/

package processing.core

import android.graphics.BitmapFactory
import java.util.*

/**
 * ( begin auto-generated from PShape.xml )
 *
 * Datatype for storing shapes. Processing can currently load and display
 * SVG (Scalable Vector Graphics) shapes. Before a shape is used, it must
 * be loaded with the **loadShape()** function. The **shape()**
 * function is used to draw the shape to the display window. The
 * **PShape** object contain a group of methods, linked below, that can
 * operate on the shape data.
 * <br></br><br></br>
 * The **loadShape()** function supports SVG files created with Inkscape
 * and Adobe Illustrator. It is not a full SVG implementation, but offers
 * some straightforward support for handling vector data.
 *
 * ( end auto-generated )
 * <h3>Advanced</h3>
 *
 * In-progress class to handle shape data, currently to be considered of
 * alpha or beta quality. Major structural work may be performed on this class
 * after the release of Processing 1.0. Such changes may include:
 *
 *
 *  *  addition of proper accessors to read shape vertex and coloring data
 * (this is the second most important part of having a PShape class after all).
 *  *  a means of creating PShape objects ala beginShape() and endShape().
 *  *  load(), update(), and cache methods ala PImage, so that shapes can
 * have renderer-specific optimizations, such as vertex arrays in OpenGL.
 *  *  splitting this class into multiple classes to handle different
 * varieties of shape data (primitives vs collections of vertices vs paths)
 *  *  change of package declaration, for instance moving the code into
 * package processing.shape (if the code grows too much).
 *
 *
 *
 * For the time being, this class and its shape() and loadShape() friends in
 * PApplet exist as placeholders for more exciting things to come. If you'd
 * like to work with this class, make a subclass (see how PShapeSVG works)
 * and you can play with its internal methods all you like.
 *
 *
 * Library developers are encouraged to create PShape objects when loading
 * shape data, so that they can eventually hook into the bounty that will be
 * the PShape interface, and the ease of loadShape() and shape().
 *
 * @webref shape
 * @usage Web &amp; Application
 * @see PApplet.loadShape
 * @see PApplet.createShape
 * @see PApplet.shapeMode
 * @instanceName sh any variable of type PShape
 */
open class PShape : PConstants {
    @JvmField
    var name: String? = null

    @JvmField
    protected var nameTable: MutableMap<String?, PShape?>? = null

    /** The shape type, one of GROUP, PRIMITIVE, PATH, or GEOMETRY.  */
    /** The shape type, one of GROUP, PRIMITIVE, PATH, or GEOMETRY.  */
    @JvmField
    var family: Int

    /** ELLIPSE, LINE, QUAD; TRIANGLE_FAN, QUAD_STRIP; etc.  */

    @JvmField
    var kind = 0

    @JvmField
    protected var matrix: PMatrix? = null

    @JvmField
    protected var pstextureMode = 0

    /** Texture or image data associated with this shape.  */
    @JvmField
    protected var image: PImage? = null

    @JvmField
    protected var imagePath: String? = null//checkBounds();

    /**
     * Get the width of the drawing area (not necessarily the shape boundary).
     */
    /**
     * ( begin auto-generated from PShape_width.xml )
     *
     * The width of the PShape document.
     *
     * ( end auto-generated )
     * @webref pshape:field
     * @usage web_application
     * @brief     Shape document width
     * @see PShape.mheight
     */
    @JvmField
    var mwidth = 0f    /* change the property_name to mwidth from width
                          because of the inheritance model and getters
                          and setters by kotlin itself creates unusual
                          JVM syntax declaration clash errors*/

    /**
     * Get the height of the drawing area (not necessarily the shape boundary).
     */
    /**
     * ( begin auto-generated from PShape_height.xml )
     *
     * The height of the PShape document.
     *
     * ( end auto-generated )
     * @webref pshape:field
     * @usage web_application
     * @brief     Shape document height
     * @see PShape.mwidth
     */
    @JvmField
    var mheight = 0f     /* change the property_name to mheight from height
                          because of the inheritance model and getters and
                          setters by kotlin itself creates unusual JVM
                          syntax declaration clash errors*/

    /**
     * Get the depth of the shape area (not necessarily the shape boundary). Only makes sense for 3D PShape subclasses,
     * such as PShape3D.
     */
    @JvmField
     var mdepth = 0f    /* change the property_name to mdepth from depth
                          because of the inheritance model and getters
                          and setters by kotlin itself creates unusual
                          JVM syntax declaration clash errors*/

    @JvmField
    var g: PGraphics? = null

    // set to false if the object is hidden in the layers palette
    @JvmField
    protected var visible = true

    /** Retained shape being created with beginShape/endShape  */

    @JvmField
    protected var openShape = false

    @JvmField
    protected var openContour = false

    @JvmField
    protected var mstroke = false

    @JvmField
    protected var strokeColor = 0

    @JvmField
    // default is 1 = 0f
    protected var mstrokeWeight = 1f     /* change the property_name to mstrokeWeight from
                                            strokeWeight because of the inheritance model
                                            and getters and setters by kotlin itself creates
                                            unusual JVM syntax declaration clash errors*/


    @JvmField
    protected var mstrokeCap = 0        /* change the property_name to mstrokeCap from
                                            strokeCap because of the inheritance model
                                            and getters and setters by kotlin itself creates
                                            unusual JVM syntax declaration clash errors*/

    @JvmField
    protected var mstrokeJoin = 0       /* change the property_name to mstrokeJoin from
                                            strokeJoin because of the inheritance model
                                            and getters and setters by kotlin itself creates
                                            unusual JVM syntax declaration clash errors*/

    @JvmField
    protected var psfill = false
    @JvmField
    protected var fillColor = 0

    protected var mtint = false        /* change the property_name to mtint from
                                           tint because of the inheritance model
                                           and getters and setters by kotlin itself creates
                                           unusual JVM syntax declaration clash errors*/

    @JvmField
    protected var tintColor = 0
    @JvmField
    protected var ambientColor = 0
    protected var setAmbient = false

    @JvmField
    protected var specularColor = 0

    @JvmField
    protected var emissiveColor = 0
    @JvmField
    protected var mshininess = 0f      /* change the property_name to mshininess from
                                            shininess because of the inheritance model
                                            and getters and setters by kotlin itself creates
                                            unusual JVM syntax declaration clash errors*/

    @JvmField
    protected var sphereDetailU = 0
    @JvmField
    protected var sphereDetailV = 0

    @JvmField
    protected var rectMode = 0

    @JvmField
    protected var ellipseMode = 0

    /** Temporary toggle for whether styles should be honored.  */
    @JvmField
    protected var style = true

    /** For primitive shapes in particular, params like x/y/w/h or x1/y1/x2/y2.  */
    @JvmField
    var mparams: FloatArray? = null

    @JvmField
    protected var mvertexCount = 0    /* change the property_name to mvertexCount from
                                          vertexCount because of the inheritance model
                                          and getters and setters by kotlin itself creates
                                          unusual JVM syntax declaration clash errors*/

    /**
     * When drawing POLYGON shapes, the second param is an array of length
     * VERTEX_FIELD_COUNT. When drawing PATH shapes, the second param has only
     * two variables.
     */
    @JvmField
    protected var vertices: Array<FloatArray?>? = null

    @JvmField
    protected var parent: PShape? = null
    @JvmField
    var childCount = 0

    @JvmField
    protected var mchildren: Array<PShape?>? = null

    /** Array of VERTEX, BEZIER_VERTEX, and CURVE_VERTEX calls.  */
    @JvmField
    protected var mvertexCodeCount = 0

    @JvmField
    protected var mvertexCodes: IntArray? = null

    /** True if this is a closed path.  */
    @JvmField
    protected var close = false

    // ........................................................
    // internal color for setting/calculating
    @JvmField
    protected var calcR = 0f
    @JvmField
    protected var calcG = 0f
    @JvmField
    protected var calcB = 0f
    @JvmField
    protected var calcA = 0f
    @JvmField
    protected var calcRi = 0
    @JvmField
    protected var calcGi = 0
    @JvmField
    protected var calcBi = 0
    @JvmField
    protected var calcAi = 0
    @JvmField
    protected var calcColor = 0
    @JvmField
    protected var calcAlpha = false

    /** The current colorMode  */
    @JvmField
     var colorMode = 0 // = RGB;

    /** Max value for red (or hue) set by colorMode  */
    @JvmField
     var colorModeX = 0f // = 255;

    /** Max value for green (or saturation) set by colorMode  */
    @JvmField
     var colorModeY = 0f // = 255;

    /** Max value for blue (or value) set by colorMode  */
    @JvmField
     var colorModeZ = 0f // = 255;

    /** Max value for alpha set by colorMode  */
    @JvmField
    var colorModeA = 0f // = 255;

    /** True if colors are not in the range 0..1  */
    @JvmField
    var colorModeScale = false // = true;

    /** True if colorMode(RGB, 255)  */
    @JvmField
    var colorModeDefault = false // = true;

    /** True if contains 3D data  */
    @JvmField
    var is3D = false

    @JvmField
    protected var perVertexStyles = false
    // should this be called vertices (consistent with PGraphics internals)
    // or does that hurt flexibility?
    // POINTS, LINES, xLINE_STRIP, xLINE_LOOP
    // TRIANGLES, TRIANGLE_STRIP, TRIANGLE_FAN
    // QUADS, QUAD_STRIP
    // xPOLYGON
    //  static final int PATH = 1;  // POLYGON, LINE_LOOP, LINE_STRIP
    //  static final int GROUP = 2;
    // how to handle rectmode/ellipsemode?
    // are they bitshifted into the constant?
    // CORNER, CORNERS, CENTER, (CENTER_RADIUS?)
    //  static final int RECT = 3; // could just be QUAD, but would be x1/y1/x2/y2
    //  static final int ELLIPSE = 4;
    //
    //  static final int VERTEX = 7;
    //  static final int CURVE = 5;
    //  static final int BEZIER = 6;
    // fill and stroke functions will need a pointer to the parent
    // PGraphics object.. may need some kind of createShape() fxn
    // or maybe the values are stored until draw() is called?
    // attaching images is very tricky.. it's a different type of data
    // material parameters will be thrown out,
    // except those currently supported (kinds of lights)
    // pivot point for transformations
    //  public float px;
    //  public float py;
    /**
     * @nowebref
     */
    constructor() {
        family = PConstants.GROUP
    }

    /**
     * @nowebref
     */
    constructor(family: Int) {
        this.family = family
    }

    /**
     * @nowebref
     */
    constructor(g: PGraphics, family: Int) {
        this.g = g
        this.family = family

        // Style parameters are retrieved from the current values in the renderer.
        pstextureMode = g.textureMode
        colorMode(g.colorMode,
                g.colorModeX, g.colorModeY, g.colorModeZ, g.colorModeA)

        // Initial values for fill, stroke and tint colors are also imported from
        // the renderer. This is particular relevant for primitive shapes, since is
        // not possible to set their color separately when creating them, and their
        // input vertices are actually generated at rendering time, by which the
        // color configuration of the renderer might have changed.
        psfill = g.fill
        fillColor = g.fillColor
        mstroke = g.stroke
        strokeColor = g.strokeColor
        mstrokeWeight = g.strokeWeight
        mstrokeCap = g.strokeCap
        mstrokeJoin = g.strokeJoin
        mtint = g.tint
        tintColor = g.tintColor
        setAmbient = g.setAmbient
        ambientColor = g.ambientColor
        specularColor = g.specularColor
        emissiveColor = g.emissiveColor
        mshininess = g.shininess
        sphereDetailU = g.sphereDetailU
        sphereDetailV = g.sphereDetailV

//    bezierDetail = pg.bezierDetail;
//    curveDetail = pg.curveDetail;
//    curveTightness = pg.curveTightness;
        rectMode = g.rectMode
        ellipseMode = g.ellipseMode

//    normalX = normalY = 0;
//    normalZ = 1;
//
//    normalMode = NORMAL_MODE_AUTO;

        // To make sure that the first vertex is marked as a break.
        // Same behavior as in the immediate mode.
//    breakShape = false;
        if (family == PConstants.GROUP) {
            // GROUP shapes are always marked as ended.
//      shapeCreated = true;
            // TODO why was this commented out?
        }
    }

    constructor(g: PGraphics, kind: Int, vararg params: Float) : this(g, PRIMITIVE) {
        this.kind = kind
        setParams(params)
    }

    /**
     * ( begin auto-generated from PShape_isVisible.xml )
     *
     * Returns a boolean value "true" if the image is set to be visible,
     * "false" if not. This is modified with the **setVisible()** parameter.
     * <br></br> <br></br>
     * The visibility of a shape is usually controlled by whatever program
     * created the SVG file. For instance, this parameter is controlled by
     * showing or hiding the shape in the layers palette in Adobe Illustrator.
     *
     * ( end auto-generated )
     * @webref pshape:method
     * @usage web_application
     * @brief Returns a boolean value "true" if the image is set to be visible, "false" if not
     * @see PShape.setVisible
     */
    fun isVisible(): Boolean {
        return visible
    }

    /**
     * ( begin auto-generated from PShape_setVisible.xml )
     *
     * Sets the shape to be visible or invisible. This is determined by the
     * value of the **visible** parameter.
     * <br></br> <br></br>
     * The visibility of a shape is usually controlled by whatever program
     * created the SVG file. For instance, this parameter is controlled by
     * showing or hiding the shape in the layers palette in Adobe Illustrator.
     *
     * ( end auto-generated )
     * @webref pshape:mathod
     * @usage web_application
     * @brief Sets the shape to be visible or invisible
     * @param visible "false" makes the shape invisible and "true" makes it visible
     * @see PShape.isVisible
     */
//    fun setVisible(visible: Boolean) {
//        this.visible = visible
//    }

    /**
     * ( begin auto-generated from PShape_disableStyle.xml )
     *
     * Disables the shape's style data and uses Processing's current styles.
     * Styles include attributes such as colors, stroke weight, and stroke
     * joints.
     *
     * ( end auto-generated )
     * <h3>Advanced</h3>
     * Overrides this shape's style information and uses PGraphics styles and
     * colors. Identical to ignoreStyles(true). Also disables styles for all
     * child shapes.
     * @webref pshape:method
     * @usage web_application
     * @brief     Disables the shape's style data and uses Processing styles
     * @see PShape.enableStyle
     */
    open fun disableStyle() {
        style = false
        for (i in 0 until childCount) {
            mchildren!![i]!!.disableStyle()
        }
    }

    open fun getDepth(): Float {
        return mdepth
    }

    open fun getHeight(): Float {
        return mheight
    }

    open fun getWidth(): Float {
        return mwidth
    }
    /**
     * ( begin auto-generated from PShape_enableStyle.xml )
     *
     * Enables the shape's style data and ignores Processing's current styles.
     * Styles include attributes such as colors, stroke weight, and stroke
     * joints.
     *
     * ( end auto-generated )
     *
     * @webref pshape:method
     * @usage web_application
     * @brief Enables the shape's style data and ignores the Processing styles
     * @see PShape.disableStyle
     */
    open fun enableStyle() {
        style = true
        for (i in 0 until childCount) {
            mchildren!![i]!!.enableStyle()
        }
    }
    // . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . .
    //  protected void checkBounds() {
    //    if (width == 0 || height == 0) {
    //      // calculate bounds here (also take kids into account)
    //      width = 1;
    //      height = 1;
    //    }
    //  }

    /*
  // TODO unapproved
  protected PVector getTop() {
    return getTop(null);
  }


  protected PVector getTop(PVector top) {
    if (top == null) {
      top = new PVector();
    }
    return top;
  }


  protected PVector getBottom() {
    return getBottom(null);
  }


  protected PVector getBottom(PVector bottom) {
    if (bottom == null) {
      bottom = new PVector();
    }
    return bottom;
  }
  */
    /**
     * Return true if this shape is 2D. Defaults to true.
     */
    fun is2D(): Boolean {
        return !is3D
    }

    /**
     * Return true if this shape is 3D. Defaults to false.
     */
//    fun is3D(): Boolean {
//        return is3D
//    }
//
//    fun set3D(`val`: Boolean) {
//        is3D = `val`
//    }

    //  /**
    //   * Return true if this shape requires rendering through OpenGL. Defaults to false.
    //   */
    //  // TODO unapproved
    //  public boolean isGL() {
    //    return false;
    //  }
    ///////////////////////////////////////////////////////////
    //
    // Drawing methods
    fun textureMode(mode: Int) {
        if (!openShape) {
            PGraphics.showWarning(OUTSIDE_BEGIN_END_ERROR, "textureMode()")
            return
        }
        pstextureMode = mode
    }

    fun texture(tex: PImage?) {
        if (!openShape) {
            PGraphics.showWarning(OUTSIDE_BEGIN_END_ERROR, "texture()")
            return
        }
        image = tex
    }

    fun noTexture() {
        if (!openShape) {
            PGraphics.showWarning(OUTSIDE_BEGIN_END_ERROR, "noTexture()")
            return
        }
        image = null
    }

    // TODO unapproved
    protected open fun solid(solid: Boolean) {}

    /**
     * @webref shape:vertex
     * @brief Starts a new contour
     * @see PShape.endContour
     */
    fun beginContour() {
        if (!openShape) {
            PGraphics.showWarning(OUTSIDE_BEGIN_END_ERROR, "beginContour()")
            return
        }
        if (family == PConstants.GROUP) {
            PGraphics.showWarning("Cannot begin contour in GROUP shapes")
            return
        }
        if (openContour) {
            PGraphics.showWarning("Already called beginContour().")
            return
        }
        openContour = true
        beginContourImpl()
    }

    protected open fun beginContourImpl() {
        if (mvertexCodes == null) {
            mvertexCodes = IntArray(10)
        } else if (mvertexCodes!!.size == mvertexCodeCount) {
            mvertexCodes = PApplet.expand(mvertexCodes)
        }
        mvertexCodes!![mvertexCodeCount++] = PConstants.BREAK
    }

    /**
     * @webref shape:vertex
     * @brief Ends a contour
     * @see PShape.beginContour
     */
    fun endContour() {
        if (!openShape) {
            PGraphics.showWarning(OUTSIDE_BEGIN_END_ERROR, "endContour()")
            return
        }
        if (family == PConstants.GROUP) {
            PGraphics.showWarning("Cannot end contour in GROUP shapes")
            return
        }
        if (!openContour) {
            PGraphics.showWarning("Need to call beginContour() first.")
            return
        }
        endContourImpl()
        openContour = false
    }

    protected open fun endContourImpl() {

    }

    open fun vertex(x: Float, y: Float) {
        if (vertices == null) {
            vertices = Array(10) { FloatArray(2) }
        } else if (vertices!!.size == mvertexCount) {
            vertices = PApplet.expand(vertices) as Array<FloatArray?>
        }
        vertices!![mvertexCount++] = floatArrayOf(x, y)
        if (mvertexCodes == null) {
            mvertexCodes = IntArray(10)
        } else if (mvertexCodes!!.size == mvertexCodeCount) {
            mvertexCodes = PApplet.expand(mvertexCodes)
        }
        mvertexCodes!![mvertexCodeCount++] = PConstants.VERTEX
        if (x > mwidth) {
            mwidth = x
        }
        if (y > mheight) {
            mheight = y
        }
    }

    open fun vertex(x: Float, y: Float, u: Float, v: Float) {

    }

    open fun vertex(x: Float, y: Float, z: Float) {
        vertex(x, y) // maybe? maybe not?
    }

    open fun vertex(x: Float, y: Float, z: Float, u: Float, v: Float) {

    }

    open fun normal(nx: Float, ny: Float, nz: Float) {

    }

    open fun attribPosition(name: String?, x: Float, y: Float, z: Float) {

    }

    open fun attribNormal(name: String?, nx: Float, ny: Float, nz: Float) {

    }

    open fun attribColor(name: String?, color: Int) {

    }

    open fun attrib(name: String?, vararg values: Float) {

    }

    open fun attrib(name: String?, vararg values: Int) {

    }

    open fun attrib(name: String?, vararg values: Boolean) {

    }


    /**
     * @webref pshape:method
     * @brief Starts the creation of a new PShape
     * @see PApplet.endShape
     */
    @JvmOverloads
    fun beginShape(kind: Int = PConstants.POLYGON) {
        this.kind = kind
        openShape = true
    }

    /**
     * @webref pshape:method
     * @brief Finishes the creation of a new PShape
     * @see PApplet.beginShape
     */
    fun endShape() {
        endShape(PConstants.OPEN)
    }

    open fun endShape(mode: Int) {
        if (family == PConstants.GROUP) {
            PGraphics.showWarning("Cannot end GROUP shape")
            return
        }
        if (!openShape) {
            PGraphics.showWarning("Need to call beginShape() first")
            return
        }
        close = mode == PConstants.CLOSE

        // this is the state of the shape
        openShape = false
    }

    //////////////////////////////////////////////////////////////
    // STROKE CAP/JOIN/WEIGHT
    fun strokeWeight(weight: Float) {
        if (!openShape) {
            PGraphics.showWarning(OUTSIDE_BEGIN_END_ERROR, "strokeWeight()")
            return
        }
        mstrokeWeight = weight
    }

    fun strokeJoin(join: Int) {
        if (!openShape) {
            PGraphics.showWarning(OUTSIDE_BEGIN_END_ERROR, "strokeJoin()")
            return
        }
        mstrokeJoin = join
    }

    fun strokeCap(cap: Int) {
        if (!openShape) {
            PGraphics.showWarning(OUTSIDE_BEGIN_END_ERROR, "strokeCap()")
            return
        }
        mstrokeCap = cap
    }

    //////////////////////////////////////////////////////////////
    // FILL COLOR
    fun noFill() {
        if (!openShape) {
            PGraphics.showWarning(OUTSIDE_BEGIN_END_ERROR, "noFill()")
            return
        }
        psfill = false
        fillColor = 0x0
        if (!setAmbient) {
            ambientColor = fillColor
        }
    }

    fun fill(rgb: Int) {
        if (!openShape) {
            PGraphics.showWarning(OUTSIDE_BEGIN_END_ERROR, "fill()")
            return
        }
        psfill = true
        colorCalc(rgb)
        fillColor = calcColor
        if (!setAmbient) {
            ambientColor = fillColor
        }
    }

    fun fill(rgb: Int, alpha: Float) {
        if (!openShape) {
            PGraphics.showWarning(OUTSIDE_BEGIN_END_ERROR, "fill()")
            return
        }
        psfill = true
        colorCalc(rgb, alpha)
        fillColor = calcColor
        if (!setAmbient) {
            ambientColor = fillColor
        }
    }

    fun fill(gray: Float) {
        if (!openShape) {
            PGraphics.showWarning(OUTSIDE_BEGIN_END_ERROR, "fill()")
            return
        }
        psfill = true
        colorCalc(gray)
        fillColor = calcColor
        if (!setAmbient) {
            ambientColor = fillColor
        }
    }

    fun fill(gray: Float, alpha: Float) {
        if (!openShape) {
            PGraphics.showWarning(OUTSIDE_BEGIN_END_ERROR, "fill()")
            return
        }
        psfill = true
        colorCalc(gray, alpha)
        fillColor = calcColor
        if (!setAmbient) {
            ambient(fillColor)
            setAmbient = false
        }
        if (!setAmbient) {
            ambientColor = fillColor
        }
    }

    fun fill(x: Float, y: Float, z: Float) {
        if (!openShape) {
            PGraphics.showWarning(OUTSIDE_BEGIN_END_ERROR, "fill()")
            return
        }
        psfill = true
        colorCalc(x, y, z)
        fillColor = calcColor
        if (!setAmbient) {
            ambientColor = fillColor
        }
    }

    fun fill(x: Float, y: Float, z: Float, a: Float) {
        if (!openShape) {
            PGraphics.showWarning(OUTSIDE_BEGIN_END_ERROR, "fill()")
            return
        }
        psfill = true
        colorCalc(x, y, z, a)
        fillColor = calcColor
        if (!setAmbient) {
            ambientColor = fillColor
        }
    }

    //////////////////////////////////////////////////////////////
    // STROKE COLOR
    fun noStroke() {
        if (!openShape) {
            PGraphics.showWarning(OUTSIDE_BEGIN_END_ERROR, "noStroke()")
            return
        }
        mstroke = false
    }

    fun stroke(rgb: Int) {
        if (!openShape) {
            PGraphics.showWarning(OUTSIDE_BEGIN_END_ERROR, "stroke()")
            return
        }
        mstroke = true
        colorCalc(rgb)
        strokeColor = calcColor
    }

    fun stroke(rgb: Int, alpha: Float) {
        if (!openShape) {
            PGraphics.showWarning(OUTSIDE_BEGIN_END_ERROR, "stroke()")
            return
        }
        mstroke = true
        colorCalc(rgb, alpha)
        strokeColor = calcColor
    }

    fun stroke(gray: Float) {
        if (!openShape) {
            PGraphics.showWarning(OUTSIDE_BEGIN_END_ERROR, "stroke()")
            return
        }
        mstroke = true
        colorCalc(gray)
        strokeColor = calcColor
    }

    fun stroke(gray: Float, alpha: Float) {
        if (!openShape) {
            PGraphics.showWarning(OUTSIDE_BEGIN_END_ERROR, "stroke()")
            return
        }
        mstroke = true
        colorCalc(gray, alpha)
        strokeColor = calcColor
    }

    fun stroke(x: Float, y: Float, z: Float) {
        if (!openShape) {
            PGraphics.showWarning(OUTSIDE_BEGIN_END_ERROR, "stroke()")
            return
        }
        mstroke = true
        colorCalc(x, y, z)
        strokeColor = calcColor
    }

    fun stroke(x: Float, y: Float, z: Float, alpha: Float) {
        if (!openShape) {
            PGraphics.showWarning(OUTSIDE_BEGIN_END_ERROR, "stroke()")
            return
        }
        mstroke = true
        colorCalc(x, y, z, alpha)
        strokeColor = calcColor
    }

    //////////////////////////////////////////////////////////////
    // TINT COLOR
    fun noTint() {
        if (!openShape) {
            PGraphics.showWarning(OUTSIDE_BEGIN_END_ERROR, "noTint()")
            return
        }
        mtint = false
    }

    fun tint(rgb: Int) {
        if (!openShape) {
            PGraphics.showWarning(OUTSIDE_BEGIN_END_ERROR, "tint()")
            return
        }
        mtint = true
        colorCalc(rgb)
        tintColor = calcColor
    }

    fun tint(rgb: Int, alpha: Float) {
        if (!openShape) {
            PGraphics.showWarning(OUTSIDE_BEGIN_END_ERROR, "tint()")
            return
        }
        mtint = true
        colorCalc(rgb, alpha)
        tintColor = calcColor
    }

    fun tint(gray: Float) {
        if (!openShape) {
            PGraphics.showWarning(OUTSIDE_BEGIN_END_ERROR, "tint()")
            return
        }
        mtint = true
        colorCalc(gray)
        tintColor = calcColor
    }

    fun tint(gray: Float, alpha: Float) {
        if (!openShape) {
            PGraphics.showWarning(OUTSIDE_BEGIN_END_ERROR, "tint()")
            return
        }
        mtint = true
        colorCalc(gray, alpha)
        tintColor = calcColor
    }

    fun tint(x: Float, y: Float, z: Float) {
        if (!openShape) {
            PGraphics.showWarning(OUTSIDE_BEGIN_END_ERROR, "tint()")
            return
        }
        mtint = true
        colorCalc(x, y, z)
        tintColor = calcColor
    }

    fun tint(x: Float, y: Float, z: Float, alpha: Float) {
        if (!openShape) {
            PGraphics.showWarning(OUTSIDE_BEGIN_END_ERROR, "tint()")
            return
        }
        mtint = true
        colorCalc(x, y, z, alpha)
        tintColor = calcColor
    }

    //////////////////////////////////////////////////////////////
    // Ambient set/update
    fun ambient(rgb: Int) {
        if (!openShape) {
            PGraphics.showWarning(OUTSIDE_BEGIN_END_ERROR, "ambient()")
            return
        }
        setAmbient = true
        colorCalc(rgb)
        ambientColor = calcColor
    }

    fun ambient(gray: Float) {
        if (!openShape) {
            PGraphics.showWarning(OUTSIDE_BEGIN_END_ERROR, "ambient()")
            return
        }
        setAmbient = true
        colorCalc(gray)
        ambientColor = calcColor
    }

    fun ambient(x: Float, y: Float, z: Float) {
        if (!openShape) {
            PGraphics.showWarning(OUTSIDE_BEGIN_END_ERROR, "ambient()")
            return
        }
        setAmbient = true
        colorCalc(x, y, z)
        ambientColor = calcColor
    }

    //////////////////////////////////////////////////////////////
    // Specular set/update
    fun specular(rgb: Int) {
        if (!openShape) {
            PGraphics.showWarning(OUTSIDE_BEGIN_END_ERROR, "specular()")
            return
        }
        colorCalc(rgb)
        specularColor = calcColor
    }

    fun specular(gray: Float) {
        if (!openShape) {
            PGraphics.showWarning(OUTSIDE_BEGIN_END_ERROR, "specular()")
            return
        }
        colorCalc(gray)
        specularColor = calcColor
    }

    fun specular(x: Float, y: Float, z: Float) {
        if (!openShape) {
            PGraphics.showWarning(OUTSIDE_BEGIN_END_ERROR, "specular()")
            return
        }
        colorCalc(x, y, z)
        specularColor = calcColor
    }

    //////////////////////////////////////////////////////////////

    // Emissive set/update

    fun emissive(rgb: Int) {
        if (!openShape) {
            PGraphics.showWarning(OUTSIDE_BEGIN_END_ERROR, "emissive()")
            return
        }
        colorCalc(rgb)
        emissiveColor = calcColor
    }

    fun emissive(gray: Float) {
        if (!openShape) {
            PGraphics.showWarning(OUTSIDE_BEGIN_END_ERROR, "emissive()")
            return
        }
        colorCalc(gray)
        emissiveColor = calcColor
    }

    fun emissive(x: Float, y: Float, z: Float) {
        if (!openShape) {
            PGraphics.showWarning(OUTSIDE_BEGIN_END_ERROR, "emissive()")
            return
        }
        colorCalc(x, y, z)
        emissiveColor = calcColor
    }

    //////////////////////////////////////////////////////////////

    // Shininess set/update

    fun shininess(shine: Float) {
        if (!openShape) {
            PGraphics.showWarning(OUTSIDE_BEGIN_END_ERROR, "shininess()")
            return
        }
        mshininess = shine
    }

    ///////////////////////////////////////////////////////////
    //
    // Bezier curves
    open fun bezierDetail(detail: Int) {

    }

    open fun bezierVertex(x2: Float, y2: Float,
                          x3: Float, y3: Float,
                          x4: Float, y4: Float) {
        if (vertices == null) {
            vertices = arrayOfNulls(10)
        } else if (mvertexCount + 2 >= vertices!!.size) {
            vertices = PApplet.expand(vertices) as Array<FloatArray?>
        }
        vertices!![mvertexCount++] = floatArrayOf(x2, y2)
        vertices!![mvertexCount++] = floatArrayOf(x3, y3)
        vertices!![mvertexCount++] = floatArrayOf(x4, y4)

        // vertexCodes must be allocated because a vertex() call is required
        if (mvertexCodes!!.size == mvertexCodeCount) {
            mvertexCodes = PApplet.expand(mvertexCodes)
        }
        mvertexCodes!![mvertexCodeCount++] = PConstants.BEZIER_VERTEX
        if (x4 > mwidth) {
            mwidth = x4
        }
        if (y4 > mheight) {
            mheight = y4
        }
    }

    open fun bezierVertex(x2: Float, y2: Float, z2: Float,
                          x3: Float, y3: Float, z3: Float,
                          x4: Float, y4: Float, z4: Float) {
    }

    open fun quadraticVertex(cx: Float, cy: Float,
                             x3: Float, y3: Float) {
        if (vertices == null) {
            vertices = arrayOfNulls(10)
        } else if (mvertexCount + 1 >= vertices!!.size) {
            vertices = PApplet.expand(vertices) as Array<FloatArray?>
        }
        vertices!![mvertexCount++] = floatArrayOf(cx, cy)
        vertices!![mvertexCount++] = floatArrayOf(x3, y3)

        // vertexCodes must be allocated because a vertex() call is required
        if (mvertexCodes!!.size == mvertexCodeCount) {
            mvertexCodes = PApplet.expand(mvertexCodes)
        }
        mvertexCodes!![mvertexCodeCount++] = PConstants.QUADRATIC_VERTEX
        if (x3 > mwidth) {
            mwidth = x3
        }
        if (y3 > mheight) {
            mheight = y3
        }
    }

    open fun quadraticVertex(cx: Float, cy: Float, cz: Float,
                             x3: Float, y3: Float, z3: Float) {
    }

    ///////////////////////////////////////////////////////////
    //
    // Catmull-Rom curves
    open fun curveDetail(detail: Int) {

    }

    open fun curveTightness(tightness: Float) {

    }

    open fun curveVertex(x: Float, y: Float) {

    }
    open fun curveVertex(x: Float, y: Float, z: Float) {

    }

    // . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . .
    /*
  boolean strokeSaved;
  int strokeColorSaved;
  float strokeWeightSaved;
  int strokeCapSaved;
  int strokeJoinSaved;

  boolean fillSaved;
  int fillColorSaved;

  int rectModeSaved;
  int ellipseModeSaved;
  int shapeModeSaved;
  */
    protected open fun pre(g: PGraphics?) {
        if (matrix != null) {
            g?.pushMatrix()
            g?.applyMatrix(matrix)
        }

        /*
    strokeSaved = g.stroke;
    strokeColorSaved = g.strokeColor;
    strokeWeightSaved = g.strokeWeight;
    strokeCapSaved = g.strokeCap;
    strokeJoinSaved = g.strokeJoin;

    fillSaved = g.fill;
    fillColorSaved = g.fillColor;

    rectModeSaved = g.rectMode;
    ellipseModeSaved = g.ellipseMode;
    shapeModeSaved = g.shapeMode;
    */if (style) {
            g?.pushStyle()
            styles(g)
        }
    }

    protected open fun styles(g: PGraphics?) {
        // should not be necessary because using only the int version of color
        //parent.colorMode(PConstants.RGB, 255);
        if (mstroke) {
            g?.stroke(strokeColor)
            g?.strokeWeight(mstrokeWeight)
            g?.strokeCap(mstrokeCap)
            g?.strokeJoin(mstrokeJoin)
        } else {
            g?.noStroke()
        }
        if (psfill) {
            //System.out.println("filling " + PApplet.hex(fillColor));
            g?.fill(fillColor)
        } else {
            g?.noFill()
        }
    }

    protected open fun post(g: PGraphics?) {
//    for (int i = 0; i < childCount; i++) {
//      children[i].draw(g);
//    }

        /*
    // TODO this is not sufficient, since not saving fillR et al.
    g.stroke = strokeSaved;
    g.strokeColor = strokeColorSaved;
    g.strokeWeight = strokeWeightSaved;
    g.strokeCap = strokeCapSaved;
    g.strokeJoin = strokeJoinSaved;

    g.fill = fillSaved;
    g.fillColor = fillColorSaved;

    g.ellipseMode = ellipseModeSaved;
    */
        if (matrix != null) {
            g?.popMatrix()
        }
        if (style) {
            g?.popStyle()
        }
    }
    ////////////////////////////////////////////////////////////////////////
    /**
     * Called by the following (the shape() command adds the g)
     * PShape s = loadShape("blah.svg");
     * shape(s);
     */
    open fun draw(g: PGraphics?) {
        if (visible) {
            pre(g)
            drawImpl(g)
            post(g)
        }
    }

    /**
     * Draws the SVG document.
     */
    protected open fun drawImpl(g: PGraphics?) {
        if (family == PConstants.GROUP) {
            drawGroup(g)
        } else if (family == PRIMITIVE) {
            drawPrimitive(g)
        } else if (family == GEOMETRY) {
            // Not same as path: `kind` matters.
//      drawPath(g);
            drawGeometry(g)
        } else if (family == PATH) {
            drawPath(g)
        }
    }

    protected fun drawGroup(g: PGraphics?) {
        for (i in 0 until childCount) {
            mchildren!![i]!!.draw(g)
        }
    }

    protected fun drawPrimitive(g: PGraphics?) {
        if (kind == PConstants.POINT) {
            g?.point(mparams!![0], mparams!![1])
        } else if (kind == PConstants.LINE) {
            if (mparams!!.size == 4) {  // 2D
                g?.line(mparams!![0], mparams!![1],
                        mparams!![2], mparams!![3])
            } else {  // 3D
                g?.line(mparams!![0], mparams!![1], mparams!![2],
                        mparams!![3], mparams!![4], mparams!![5])
            }
        } else if (kind == PConstants.TRIANGLE) {
            g?.triangle(mparams!![0], mparams!![1],
                    mparams!![2], mparams!![3],
                    mparams!![4], mparams!![5])
        } else if (kind == PConstants.QUAD) {
            g?.quad(mparams!![0], mparams!![1],
                    mparams!![2], mparams!![3],
                    mparams!![4], mparams!![5],
                    mparams!![6], mparams!![7])
        } else if (kind == PConstants.RECT) {
            if (imagePath != null) {
                loadImage(g)
            }
            if (image != null) {
                val oldMode = g!!.imageMode
                g?.imageMode(PConstants.CORNER)
                g?.image(image, mparams!![0], mparams!![1], mparams!![2], mparams!![3])
                g?.imageMode(oldMode)
            } else {
                val oldMode = g!!.rectMode
                g?.rectMode(rectMode)
                if (mparams!!.size == 4) {
                    g?.rect(mparams!![0], mparams!![1],
                            mparams!![2], mparams!![3])
                } else if (mparams!!.size == 5) {
                    g?.rect(mparams!![0], mparams!![1],
                            mparams!![2], mparams!![3],
                            mparams!![4])
                } else if (mparams!!.size == 8) {
                    g?.rect(mparams!![0], mparams!![1],
                            mparams!![2], mparams!![3],
                            mparams!![4], mparams!![5],
                            mparams!![6], mparams!![7])
                }
                g?.rectMode(oldMode)
            }
        } else if (kind == PConstants.ELLIPSE) {
            val oldMode = g!!.ellipseMode
            g?.ellipseMode(ellipseMode)
            g?.ellipse(mparams!![0], mparams!![1],
                    mparams!![2], mparams!![3])
            g?.ellipseMode(oldMode)
        } else if (kind == PConstants.ARC) {
            val oldMode = g!!.ellipseMode
            g?.ellipseMode(ellipseMode)
            if (mparams!!.size == 6) {
                g?.arc(mparams!![0], mparams!![1],
                        mparams!![2], mparams!![3],
                        mparams!![4], mparams!![5])
            } else if (mparams!!.size == 7) {
                g?.arc(mparams!![0], mparams!![1],
                        mparams!![2], mparams!![3],
                        mparams!![4], mparams!![5],
                        mparams!![6].toInt())
            }
            g?.ellipseMode(oldMode)
        } else if (kind == PConstants.BOX) {
            if (mparams!!.size == 1) {
                g?.box(mparams!![0])
            } else {
                g?.box(mparams!![0], mparams!![1], mparams!![2])
            }
        } else if (kind == PConstants.SPHERE) {
            g?.sphere(mparams!![0])
        }
    }

    protected open fun drawGeometry(g: PGraphics?) {
        // get cache object using g.
        g?.beginShape(kind)
        if (style) {
            for (i in 0 until mvertexCount) {
                g?.vertex(vertices!![i])
            }
        } else {
            for (i in 0 until mvertexCount) {
                val vert = vertices!![i]
                if (vert!![PConstants.Z] == 0F) {
                    g?.vertex(vert[PConstants.X], vert[PConstants.Y])
                } else {
                    g?.vertex(vert[PConstants.X], vert[PConstants.Y], vert[PConstants.Z])
                }
            }
        }
        g?.endShape(if (close) PConstants.CLOSE else PConstants.OPEN)
    }

    /*
  protected void drawPath(PGraphics g) {
    g.beginShape();
    for (int j = 0; j < childCount; j++) {
      if (j > 0) g.breakShape();
      int count = children[j].vertexCount;
      float[][] vert = children[j].vertices;
      int[] code = children[j].vertexCodes;

      for (int i = 0; i < count; i++) {
        if (style) {
          if (children[j].fill) {
            g.fill(vert[i][R], vert[i][G], vert[i][B]);
          } else {
            g.noFill();
          }
          if (children[j].stroke) {
            g.stroke(vert[i][R], vert[i][G], vert[i][B]);
          } else {
            g.noStroke();
          }
        }
        g.edge(vert[i][EDGE] == 1);

        if (code[i] == VERTEX) {
          g.vertex(vert[i]);

        } else if (code[i] == BEZIER_VERTEX) {
          float z0 = vert[i+0][Z];
          float z1 = vert[i+1][Z];
          float z2 = vert[i+2][Z];
          if (z0 == 0 && z1 == 0 && z2 == 0) {
            g.bezierVertex(vert[i+0][X], vert[i+0][Y], z0,
                           vert[i+1][X], vert[i+1][Y], z1,
                           vert[i+2][X], vert[i+2][Y], z2);
          } else {
            g.bezierVertex(vert[i+0][X], vert[i+0][Y],
                           vert[i+1][X], vert[i+1][Y],
                           vert[i+2][X], vert[i+2][Y]);
          }
        } else if (code[i] == CURVE_VERTEX) {
          float z = vert[i][Z];
          if (z == 0) {
            g.curveVertex(vert[i][X], vert[i][Y]);
          } else {
            g.curveVertex(vert[i][X], vert[i][Y], z);
          }
        }
      }
    }
    g.endShape();
  }
  */
    protected fun drawPath(g: PGraphics?) {
        // Paths might be empty (go figure)
        // http://dev.processing.org/bugs/show_bug.cgi?id=982
        if (vertices == null) return
        var insideContour = false
        g?.beginShape()
        if (mvertexCodeCount == 0) {  // each point is a simple vertex
            if (vertices!![0]!!.size == 2) {  // drawing 2D vertices
                for (i in 0 until mvertexCount) {
                    g?.vertex(vertices!![i]!![PConstants.X], vertices!![i]!![PConstants.Y])
                }
            } else {  // drawing 3D vertices
                for (i in 0 until mvertexCount) {
                    g?.vertex(vertices!![i]!![PConstants.X], vertices!![i]!![PConstants.Y], vertices!![i]!![PConstants.Z])
                }
            }
        } else {  // coded set of vertices
            var index = 0
            if (vertices!![0]!!.size == 2) {  // drawing a 2D path
                for (j in 0 until mvertexCodeCount) {
                    when (mvertexCodes!![j]) {
                        PConstants.VERTEX -> {
                            g?.vertex(vertices!![index]!![PConstants.X], vertices!![index]!![PConstants.Y])
                            index++
                        }
                        PConstants.QUADRATIC_VERTEX -> {
                            g?.quadraticVertex(vertices!![index + 0]!![PConstants.X], vertices!![index + 0]!![PConstants.Y],
                                    vertices!![index + 1]!![PConstants.X], vertices!![index + 1]!![PConstants.Y])
                            index += 2
                        }
                        PConstants.BEZIER_VERTEX -> {
                            g?.bezierVertex(vertices!![index + 0]!![PConstants.X], vertices!![index + 0]!![PConstants.Y],
                                    vertices!![index + 1]!![PConstants.X], vertices!![index + 1]!![PConstants.Y],
                                    vertices!![index + 2]!![PConstants.X], vertices!![index + 2]!![PConstants.Y])
                            index += 3
                        }
                        PConstants.CURVE_VERTEX -> {
                            g?.curveVertex(vertices!![index]!![PConstants.X], vertices!![index]!![PConstants.Y])
                            index++
                        }
                        PConstants.BREAK -> {
                            if (insideContour) {
                                g?.endContour()
                            }
                            g?.beginContour()
                            insideContour = true
                        }
                    }
                }
            } else {  // drawing a 3D path
                for (j in 0 until mvertexCodeCount) {
                    when (mvertexCodes!![j]) {
                        PConstants.VERTEX -> {
                            g?.vertex(vertices!![index]!![PConstants.X], vertices!![index]!![PConstants.Y], vertices!![index]!![PConstants.Z])
                            index++
                        }
                        PConstants.QUADRATIC_VERTEX -> {
                            g?.quadraticVertex(vertices!![index + 0]!![PConstants.X], vertices!![index + 0]!![PConstants.Y], vertices!![index + 0]!![PConstants.Z],
                                    vertices!![index + 1]!![PConstants.X], vertices!![index + 1]!![PConstants.Y], vertices!![index + 0]!![PConstants.Z])
                            index += 2
                        }
                        PConstants.BEZIER_VERTEX -> {
                            g?.bezierVertex(vertices!![index + 0]!![PConstants.X], vertices!![index + 0]!![PConstants.Y], vertices!![index + 0]!![PConstants.Z],
                                    vertices!![index + 1]!![PConstants.X], vertices!![index + 1]!![PConstants.Y], vertices!![index + 1]!![PConstants.Z],
                                    vertices!![index + 2]!![PConstants.X], vertices!![index + 2]!![PConstants.Y], vertices!![index + 2]!![PConstants.Z])
                            index += 3
                        }
                        PConstants.CURVE_VERTEX -> {
                            g?.curveVertex(vertices!![index]!![PConstants.X], vertices!![index]!![PConstants.Y], vertices!![index]!![PConstants.Z])
                            index++
                        }
                        PConstants.BREAK -> {
                            if (insideContour) {
                                g?.endContour()
                            }
                            g?.beginContour()
                            insideContour = true
                        }
                    }
                }
            }
        }
        if (insideContour) {
            g?.endContour()
        }
        g?.endShape(if (close) PConstants.CLOSE else PConstants.OPEN)
    }

    private fun loadImage(g: PGraphics?) {
        if (imagePath!!.startsWith("data:image")) {
            loadBase64Image()
        }
        if (imagePath!!.startsWith("file://")) {
            loadFileSystemImage(g)
        }
        imagePath = null
    }

    private fun loadFileSystemImage(g: PGraphics?) {
        imagePath = imagePath!!.substring(7)
        val loadedImage = g?.parent?.loadImage(imagePath)
        if (loadedImage == null) {
            System.err.println("Error loading image file: $imagePath")
        } else {
            setTexture(loadedImage)
        }
    }

    private fun loadBase64Image() {
        val parts = imagePath!!.split(";base64,").toTypedArray()
        val extension = parts[0].substring(11)
        val encodedData = parts[1]

//    byte[] decodedBytes = DatatypeConverter.parseBase64Binary(encodedData);
        val decodedBytes = parseHexBinary(encodedData)
        if (decodedBytes == null) {
            System.err.println("Decode Error on image: " + imagePath!!.substring(0, 20))
            return
        }

//    Image awtImage = new ImageIcon(decodedBytes).getImage();
        val options = BitmapFactory.Options()
        options.inMutable = true
        val bmp = BitmapFactory.decodeByteArray(decodedBytes, 0, decodedBytes.size, options)

//    if (awtImage instanceof BufferedImage) {
//      BufferedImage buffImage = (BufferedImage) awtImage;
//      int space = buffImage.getColorModel().getColorSpace().getType();
//      if (space == ColorSpace.TYPE_CMYK) {
//        return;
//      }
//    }
        val loadedImage = PImage(bmp)
        if (loadedImage.width == -1) {
            // error...
        }

        // if it's a .gif image, test to see if it has transparency
        if (extension == "gif" || extension == "png" || extension == "unknown") {
            loadedImage.checkAlpha()
        }
        setTexture(loadedImage)
    }

    // . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . .
//    fun getParent(): PShape? {
//        return parent
//    }

    /**
     * @webref
     * @brief Returns the number of children
     */
//    fun getChildCount(): Int {
//        return childCount
//    }

    /** Resize the children[] array to be in line with childCount  */
    protected fun crop() {
        // https://github.com/processing/processing/issues/3347
        if (mchildren!!.size != childCount) {
            mchildren = PApplet.subset(mchildren, 0, childCount) as Array<PShape?>
        }
    }

    fun getChildren(): Array<PShape?>? {
        crop()
        return mchildren
    }

    /**
     * ( begin auto-generated from PShape_getChild.xml )
     *
     * Extracts a child shape from a parent shape. Specify the name of the
     * shape with the **target** parameter. The shape is returned as a
     * **PShape** object, or **null** is returned if there is an error.
     *
     * ( end auto-generated )
     * @webref pshape:method
     * @usage web_application
     * @brief Returns a child element of a shape as a PShape object
     * @param index the layer position of the shape to get
     * @see PShape.addChild
     */
    fun getChild(index: Int): PShape? {
        crop()
        return mchildren!![index]
    }

    /**
     * @param target the name of the shape to get
     */
    open fun getChild(target: String): PShape? {
        if (name != null && name == target) {
            return this
        }
        if (nameTable != null) {
            val found = nameTable!![target]
            if (found != null) return found
        }
        for (i in 0 until childCount) {
            val found = mchildren!![i]!!.getChild(target)
            if (found != null) return found
        }
        return null
    }

    /**
     * Same as getChild(name), except that it first walks all the way up the
     * hierarchy to the eldest grandparent, so that children can be found anywhere.
     */
    fun findChild(target: String): PShape? {
        return if (parent == null) {
            getChild(target)
        } else {
            parent!!.findChild(target)
        }
    }
    // can't be just 'add' because that suggests additive geometry
    /**
     * @webref pshape:method
     * @brief Adds a new child
     * @param who any variable of type PShape
     * @see PShape.getChild
     */
    open fun addChild(who: PShape?) {
        if (mchildren == null) {
            mchildren = arrayOfNulls(1)
        }
        if (childCount == mchildren!!.size) {
            mchildren = PApplet.expand(mchildren) as Array<PShape?>
        }
        mchildren!![childCount++] = who
        who!!.parent = this
        if (who.name != null) {
            addName(who.name, who)
        }
    }
    // adds child who exactly at position idx in the array of children.
    /**
     * @param idx the layer position in which to insert the new child
     */
    open fun addChild(who: PShape, idx: Int) {
        if (idx < childCount) {
            if (childCount == mchildren!!.size) {
                mchildren = PApplet.expand(mchildren) as Array<PShape?>
            }

            // Copy [idx, childCount - 1] to [idx + 1, childCount]
            for (i in childCount - 1 downTo idx) {
                mchildren!![i + 1] = mchildren!![i]
            }
            childCount++
            mchildren!![idx] = who
            who.parent = this
            if (who.name != null) {
                addName(who.name, who)
            }
        }
    }

    /**
     * Remove the child shape with index idx.
     */
    open fun removeChild(idx: Int) {
        if (idx < childCount) {
            val child = mchildren!![idx]

            // Copy [idx + 1, childCount - 1] to [idx, childCount - 2]
            for (i in idx until childCount - 1) {
                mchildren!![i] = mchildren!![i + 1]
            }
            childCount--
            if (child!!.name != null && nameTable != null) {
                nameTable!!.remove(child.name)
            }
        }
    }

    /**
     * Add a shape to the name lookup table.
     */
    fun addName(nom: String?, shape: PShape?) {
        if (parent != null) {
            parent!!.addName(nom, shape)
        } else {
            if (nameTable == null) {
                nameTable = HashMap()
            }
            nameTable!![nom] = shape
        }
    }

    /**
     * Returns the index of child who.
     */
    fun getChildIndex(who: PShape): Int {
        for (i in 0 until childCount) {
            if (mchildren!![i] === who) {
                return i
            }
        }
        return -1
    }

    open fun getTessellation(): PShape? {
        return null
    }
    // . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . .

    fun getParams(): FloatArray {
        return getParams(null)
    }

    fun getParams(target: FloatArray?): FloatArray {
        var target = target
        if (target == null || target.size != mparams!!.size) {
            target = FloatArray(mparams!!.size)
        }
        PApplet.arrayCopy(mparams, target)
        return target
    }

    fun getParam(index: Int): Float {
        return mparams!![index]
    }

    protected open fun setParams(source: FloatArray) {
        if (mparams == null) {
            mparams = FloatArray(source.size)
        }
        if (source.size != mparams!!.size) {
            PGraphics.showWarning("Wrong number of parameters")
            return
        }
        PApplet.arrayCopy(source, mparams)
    }

    fun setPath(vcount: Int, verts: Array<FloatArray?>?) {
        setPath(vcount, verts, 0, null)
    }

    protected open fun setPath(vcount: Int, verts: Array<FloatArray?>?, ccount: Int, codes: IntArray?) {
        if (verts == null || verts.size < vcount) return
        if (0 < ccount && (codes == null || codes.size < ccount)) return
        val ndim: Int = verts!![0]!!.size
        mvertexCount = vcount
        vertices = Array(mvertexCount) { FloatArray(ndim) }
        for (i in 0 until mvertexCount) {
            PApplet.arrayCopy(verts[i], vertices!![i])
        }
        mvertexCodeCount = ccount
        if (0 < mvertexCodeCount) {
            mvertexCodes = IntArray(mvertexCodeCount)
            PApplet.arrayCopy(codes, mvertexCodes, mvertexCodeCount)
        }
    }

    /**
     * @webref pshape:method
     * @brief Returns the total number of vertices as an int
     * @see PShape.getVertex
     * @see PShape.setVertex
     */
    open fun getVertexCount(): Int {
        if (family == PConstants.GROUP || family == PRIMITIVE) {
            PGraphics.showWarning(NO_VERTICES_ERROR)
        }
        return mvertexCount
    }

    /**
     * @webref pshape:method
     * @brief Returns the vertex at the index position
     * @param index the location of the vertex
     * @see PShape.setVertex
     * @see PShape.getVertexCount
     */
    fun getVertex(index: Int): PVector {
        return getVertex(index, null)
    }

    /**
     * @param vec PVector to assign the data to
     */
    open fun getVertex(index: Int, vec: PVector?): PVector {
        var vec = vec
        if (vec == null) {
            vec = PVector()
        }
        val vert = vertices!![index]
        vec.x = vert!![PConstants.X]
        vec.y = vert[PConstants.Y]
        if (vert.size > 2) {
            vec.z = vert[PConstants.Z]
        } else {
            vec.z = 0F // in case this isn't a new vector
        }
        return vec
    }

    open fun getVertexX(index: Int): Float {
        return vertices!![index]!![PConstants.X]
    }

    open fun getVertexY(index: Int): Float {
        return vertices!![index]!![PConstants.Y]
    }

    open fun getVertexZ(index: Int): Float {
        return vertices!![index]!![PConstants.Z]
    }

    /**
     * @webref pshape:method
     * @brief Sets the vertex at the index position
     * @param index the location of the vertex
     * @param x the x value for the vertex
     * @param y the y value for the vertex
     * @see PShape.getVertex
     * @see PShape.getVertexCount
     */
    open fun setVertex(index: Int, x: Float, y: Float) {
        if (openShape) {
            PGraphics.showWarning(INSIDE_BEGIN_END_ERROR, "setVertex()")
            return
        }
        vertices!![index]!![PConstants.X] = x
        vertices!![index]!![PConstants.Y] = y
    }

    /**
     * @param z the z value for the vertex
     */
    open fun setVertex(index: Int, x: Float, y: Float, z: Float) {
        if (openShape) {
            PGraphics.showWarning(INSIDE_BEGIN_END_ERROR, "setVertex()")
            return
        }
        vertices!![index]!![PConstants.X] = x
        vertices!![index]!![PConstants.Y] = y
        vertices!![index]!![PConstants.Z] = z
    }

    /**
     * @param vec the PVector to define the x, y, z coordinates
     */
    open fun setVertex(index: Int, vec: PVector) {
        if (openShape) {
            PGraphics.showWarning(INSIDE_BEGIN_END_ERROR, "setVertex()")
            return
        }
        vertices!![index]!![PConstants.X] = vec.x
        vertices!![index]!![PConstants.Y] = vec.y
        if (vertices!![index]!!.size > 2) {
            vertices!![index]!![PConstants.Z] = vec.z
        } else require(!(vec.z != 0F && vec.z == vec.z)) { "Cannot set a z-coordinate on a 2D shape" }
    }

    fun getNormal(index: Int): PVector {
        return getNormal(index, null)
    }

    open fun getNormal(index: Int, vec: PVector?): PVector {
        var vec = vec
        if (vec == null) {
            vec = PVector()
        }
        vec.x = vertices!![index]!![PGraphics.NX]
        vec.y = vertices!![index]!![PGraphics.NY]
        vec.z = vertices!![index]!![PGraphics.NZ]
        return vec
    }

    open fun getNormalX(index: Int): Float {
        return vertices!![index]!![PGraphics.NX]
    }

    open fun getNormalY(index: Int): Float {
        return vertices!![index]!![PGraphics.NY]
    }

    open fun getNormalZ(index: Int): Float {
        return vertices!![index]!![PGraphics.NZ]
    }

    open fun setNormal(index: Int, nx: Float, ny: Float, nz: Float) {
        if (openShape) {
            PGraphics.showWarning(INSIDE_BEGIN_END_ERROR, "setNormal()")
            return
        }
        vertices!![index]!![PGraphics.NX] = nx
        vertices!![index]!![PGraphics.NY] = ny
        vertices!![index]!![PGraphics.NZ] = nz
    }

    open fun setAttrib(name: String?, index: Int, vararg values: Float) {}
    open fun setAttrib(name: String?, index: Int, vararg values: Int) {}
    open fun setAttrib(name: String?, index: Int, vararg values: Boolean) {}
    open fun getTextureU(index: Int): Float {
        return vertices!![index]!![PGraphics.U]
    }

    open fun getTextureV(index: Int): Float {
        return vertices!![index]!![PGraphics.V]
    }

    open fun setTextureUV(index: Int, u: Float, v: Float) {
        if (openShape) {
            PGraphics.showWarning(INSIDE_BEGIN_END_ERROR, "setTextureUV()")
            return
        }

        // make sure we allocated the vertices array and that vertex exists
        if (vertices == null ||
                index >= vertices!!.size) {
            PGraphics.showWarning("$NO_SUCH_VERTEX_ERROR ($index)", "setTextureUV()")
            return
        }
        vertices!![index]!![PGraphics.U] = u
        vertices!![index]!![PGraphics.V] = v
    }

    open fun setTextureMode(mode: Int) {
        if (openShape) {
            PGraphics.showWarning(INSIDE_BEGIN_END_ERROR, "setTextureMode()")
            return
        }
        pstextureMode = mode
    }

    open fun setTexture(tex: PImage?) {
        if (openShape) {
            PGraphics.showWarning(INSIDE_BEGIN_END_ERROR, "setTexture()")
            return
        }
        image = tex
    }

    open fun getFill(index: Int): Int {
        // make sure we allocated the vertices array and that vertex exists
        if (vertices == null ||
                index >= vertices!!.size) {
            PGraphics.showWarning("$NO_SUCH_VERTEX_ERROR ($index)", "getFill()")
            return fillColor
        }
        return if (image == null) {
            val a = (vertices!![index]!![PGraphics.A] * 255).toInt()
            val r = (vertices!![index]!![PGraphics.R] * 255).toInt()
            val g = (vertices!![index]!![PGraphics.G] * 255).toInt()
            val b = (vertices!![index]!![PGraphics.B] * 255).toInt()
            a shl 24 or (r shl 16) or (g shl 8) or b
        } else {
            0
        }
    }

    /**
     * @nowebref
     */
    open fun setFill(fill: Boolean) {
        if (openShape) {
            PGraphics.showWarning(INSIDE_BEGIN_END_ERROR, "setFill()")
            return
        }
        this.psfill = fill
    }

    /**
     * ( begin auto-generated from PShape_setFill.xml )
     *
     * The **setFill()** method defines the fill color of a **PShape**.
     * This method is used after shapes are created or when a shape is defined explicitly
     * (e.g. **createShape(RECT, 20, 20, 80, 80)**) as shown in the above example.
     * When a shape is created with **beginShape()** and **endShape()**, its
     * attributes may be changed with **fill()** and **stroke()** within
     * **beginShape()** and **endShape()**. However, after the shape is
     * created, only the **setFill()** method can define a new fill value for
     * the **PShape**.
     *
     * ( end auto-generated )
     *
     * @webref
     * @param fill
     * @brief Set the fill value
     */
    open fun setFill(fill: Int) {
        if (openShape) {
            PGraphics.showWarning(INSIDE_BEGIN_END_ERROR, "setFill()")
            return
        }
        fillColor = fill
        if (vertices != null && perVertexStyles) {
            for (i in 0 until mvertexCount) {
                setFill(i, fill)
            }
        }
    }

    /**
     * @nowebref
     */
    open fun setFill(index: Int, fill: Int) {
        if (openShape) {
            PGraphics.showWarning(INSIDE_BEGIN_END_ERROR, "setFill()")
            return
        }
        if (!perVertexStyles) {
            PGraphics.showWarning(PER_VERTEX_UNSUPPORTED, "setFill()")
            return
        }

        // make sure we allocated the vertices array and that vertex exists
        if (vertices == null || index >= vertices!!.size) {
            PGraphics.showWarning("$NO_SUCH_VERTEX_ERROR ($index)", "getFill()")
            return
        }
        if (image == null) {
            vertices!![index]!![PGraphics.A] = (fill shr 24 and 0xFF) / 255.0f
            vertices!![index]!![PGraphics.R] = (fill shr 16 and 0xFF) / 255.0f
            vertices!![index]!![PGraphics.G] = (fill shr 8 and 0xFF) / 255.0f
            vertices!![index]!![PGraphics.B] = (fill shr 0 and 0xFF) / 255.0f
        }
    }

    open fun getTint(index: Int): Int {
        // make sure we allocated the vertices array and that vertex exists
        if (vertices == null || index >= vertices!!.size) {
            PGraphics.showWarning("$NO_SUCH_VERTEX_ERROR ($index)", "getTint()")
            return tintColor
        }
        return if (image != null) {
            val a = (vertices!![index]!![PGraphics.A] * 255).toInt()
            val r = (vertices!![index]!![PGraphics.R] * 255).toInt()
            val g = (vertices!![index]!![PGraphics.G] * 255).toInt()
            val b = (vertices!![index]!![PGraphics.B] * 255).toInt()
            a shl 24 or (r shl 16) or (g shl 8) or b
        } else {
            0
        }
    }

    open fun setTint(tint: Boolean) {
        if (openShape) {
            PGraphics.showWarning(INSIDE_BEGIN_END_ERROR, "setTint()")
            return
        }
        this.mtint = tint
    }

    open fun setTint(fill: Int) {
        if (openShape) {
            PGraphics.showWarning(INSIDE_BEGIN_END_ERROR, "setTint()")
            return
        }
        tintColor = fill
        if (vertices != null) {
            for (i in vertices!!.indices) {
                setFill(i, fill)
            }
        }
    }

    open fun setTint(index: Int, tint: Int) {
        if (openShape) {
            PGraphics.showWarning(INSIDE_BEGIN_END_ERROR, "setTint()")
            return
        }

        // make sure we allocated the vertices array and that vertex exists
        if (vertices == null ||
                index >= vertices!!.size) {
            PGraphics.showWarning("$NO_SUCH_VERTEX_ERROR ($index)", "setTint()")
            return
        }
        if (image != null) {
            vertices!![index]!![PGraphics.A] = (tint shr 24 and 0xFF) / 255.0f
            vertices!![index]!![PGraphics.R] = (tint shr 16 and 0xFF) / 255.0f
            vertices!![index]!![PGraphics.G] = (tint shr 8 and 0xFF) / 255.0f
            vertices!![index]!![PGraphics.B] = (tint shr 0 and 0xFF) / 255.0f
        }
    }

    open fun getStroke(index: Int): Int {
        // make sure we allocated the vertices array and that vertex exists
        if (vertices == null ||
                index >= vertices!!.size) {
            PGraphics.showWarning("$NO_SUCH_VERTEX_ERROR ($index)", "getStroke()")
            return strokeColor
        }
        val a = (vertices!![index]!![PGraphics.SA] * 255).toInt()
        val r = (vertices!![index]!![PGraphics.SR] * 255).toInt()
        val g = (vertices!![index]!![PGraphics.SG] * 255).toInt()
        val b = (vertices!![index]!![PGraphics.SB] * 255).toInt()
        return a shl 24 or (r shl 16) or (g shl 8) or b
    }

    /**
     * @nowebref
     */
    open fun setStroke(stroke: Boolean) {
        if (openShape) {
            PGraphics.showWarning(INSIDE_BEGIN_END_ERROR, "setStroke()")
            return
        }
        this.mstroke = stroke
    }

    /**
     * ( begin auto-generated from PShape_setStroke.xml )
     *
     * The **setStroke()** method defines the outline color of a **PShape**.
     * This method is used after shapes are created or when a shape is defined
     * explicitly (e.g. **createShape(RECT, 20, 20, 80, 80)**) as shown in
     * the above example. When a shape is created with **beginShape()** and
     * **endShape()**, its attributes may be changed with **fill()** and
     * **stroke()** within **beginShape()** and **endShape()**.
     * However, after the shape is created, only the **setStroke()** method
     * can define a new stroke value for the **PShape**.
     *
     * ( end auto-generated )
     *
     * @webref
     * @param stroke
     * @brief Set the stroke value
     */
    open fun setStroke(stroke: Int) {
        if (openShape) {
            PGraphics.showWarning(INSIDE_BEGIN_END_ERROR, "setStroke()")
            return
        }
        strokeColor = stroke
        if (vertices != null && perVertexStyles) {
            for (i in vertices!!.indices) {
                setStroke(i, stroke)
            }
        }
    }

    /**
     * @nowebref
     */
    open fun setStroke(index: Int, stroke: Int) {
        if (openShape) {
            PGraphics.showWarning(INSIDE_BEGIN_END_ERROR, "setStroke()")
            return
        }
        if (!perVertexStyles) {
            PGraphics.showWarning(PER_VERTEX_UNSUPPORTED, "setStroke()")
            return
        }

        // make sure we allocated the vertices array and that vertex exists
        if (vertices == null || index >= vertices!!.size) {
            PGraphics.showWarning("$NO_SUCH_VERTEX_ERROR ($index)", "setStroke()")
            return
        }
        vertices!![index]!![PGraphics.SA] = (stroke shr 24 and 0xFF) / 255.0f
        vertices!![index]!![PGraphics.SR] = (stroke shr 16 and 0xFF) / 255.0f
        vertices!![index]!![PGraphics.SG] = (stroke shr 8 and 0xFF) / 255.0f
        vertices!![index]!![PGraphics.SB] = (stroke shr 0 and 0xFF) / 255.0f
    }

    open fun getStrokeWeight(index: Int): Float {
        // make sure we allocated the vertices array and that vertex exists
        if (vertices == null || index >= vertices!!.size) {
            PGraphics.showWarning("$NO_SUCH_VERTEX_ERROR ($index)", "getStrokeWeight()")
            return mstrokeWeight
        }
        return vertices!![index]!![PGraphics.SW]
    }

    open fun setStrokeWeight(weight: Float) {
        if (openShape) {
            PGraphics.showWarning(INSIDE_BEGIN_END_ERROR, "setStrokeWeight()")
            return
        }
        mstrokeWeight = weight
        if (vertices != null && perVertexStyles) {
            for (i in 0 until mvertexCount) {
                setStrokeWeight(i, weight)
            }
        }
    }

    open fun setStrokeWeight(index: Int, weight: Float) {
        if (openShape) {
            PGraphics.showWarning(INSIDE_BEGIN_END_ERROR, "setStrokeWeight()")
            return
        }
        if (!perVertexStyles) {
            PGraphics.showWarning(PER_VERTEX_UNSUPPORTED, "setStrokeWeight()")
            return
        }

        // make sure we allocated the vertices array and that vertex exists
        if (vertices == null || index >= vertices!!.size) {
            PGraphics.showWarning("$NO_SUCH_VERTEX_ERROR ($index)", "setStrokeWeight()")
            return
        }
        vertices!![index]!![PGraphics.SW] = weight
    }

    open fun setStrokeJoin(join: Int) {
        if (openShape) {
            PGraphics.showWarning(INSIDE_BEGIN_END_ERROR, "setStrokeJoin()")
            return
        }
        mstrokeJoin = join
    }

    open fun setStrokeCap(cap: Int) {
        if (openShape) {
            PGraphics.showWarning(INSIDE_BEGIN_END_ERROR, "setStrokeCap()")
            return
        }
        mstrokeCap = cap
    }

    open fun getAmbient(index: Int): Int {

        // make sure we allocated the vertices array and that vertex exists
        if (vertices == null || index >= vertices!!.size) {
            PGraphics.showWarning("$NO_SUCH_VERTEX_ERROR ($index)", "getAmbient()")
            return ambientColor
        }
        val r = (vertices!![index]!![PGraphics.AR] * 255).toInt()
        val g = (vertices!![index]!![PGraphics.AG] * 255).toInt()
        val b = (vertices!![index]!![PGraphics.AB] * 255).toInt()
        return -0x1000000 or (r shl 16) or (g shl 8) or b
    }

    open fun setAmbient(ambient: Int) {
        if (openShape) {
            PGraphics.showWarning(INSIDE_BEGIN_END_ERROR, "setAmbient()")
            return
        }
        ambientColor = ambient
        if (vertices != null) {
            for (i in vertices!!.indices) {
                setAmbient(i, ambient)
            }
        }
    }

    open fun setAmbient(index: Int, ambient: Int) {
        if (openShape) {
            PGraphics.showWarning(INSIDE_BEGIN_END_ERROR, "setAmbient()")
            return
        }

        // make sure we allocated the vertices array and that vertex exists
        if (vertices == null || index >= vertices!!.size) {
            PGraphics.showWarning("$NO_SUCH_VERTEX_ERROR ($index)", "setAmbient()")
            return
        }
        vertices!![index]!![PGraphics.AR] = (ambient shr 16 and 0xFF) / 255.0f
        vertices!![index]!![PGraphics.AG] = (ambient shr 8 and 0xFF) / 255.0f
        vertices!![index]!![PGraphics.AB] = (ambient shr 0 and 0xFF) / 255.0f
    }

    open fun getSpecular(index: Int): Int {
        // make sure we allocated the vertices array and that vertex exists
        if (vertices == null || index >= vertices!!.size) {
            PGraphics.showWarning("$NO_SUCH_VERTEX_ERROR ($index)", "getSpecular()")
            return specularColor
        }
        val r = (vertices!![index]!![PGraphics.SPR] * 255).toInt()
        val g = (vertices!![index]!![PGraphics.SPG] * 255).toInt()
        val b = (vertices!![index]!![PGraphics.SPB] * 255).toInt()
        return -0x1000000 or (r shl 16) or (g shl 8) or b
    }

    open fun setSpecular(specular: Int) {
        if (openShape) {
            PGraphics.showWarning(INSIDE_BEGIN_END_ERROR, "setSpecular()")
            return
        }
        specularColor = specular
        if (vertices != null) {
            for (i in vertices!!.indices) {
                setSpecular(i, specular)
            }
        }
    }

    open fun setSpecular(index: Int, specular: Int) {
        if (openShape) {
            PGraphics.showWarning(INSIDE_BEGIN_END_ERROR, "setSpecular()")
            return
        }

        // make sure we allocated the vertices array and that vertex exists
        if (vertices == null || index >= vertices!!.size) {
            PGraphics.showWarning("$NO_SUCH_VERTEX_ERROR ($index)", "setSpecular()")
            return
        }
        vertices!![index]!![PGraphics.SPR] = (specular shr 16 and 0xFF) / 255.0f
        vertices!![index]!![PGraphics.SPG] = (specular shr 8 and 0xFF) / 255.0f
        vertices!![index]!![PGraphics.SPB] = (specular shr 0 and 0xFF) / 255.0f
    }

    open fun getEmissive(index: Int): Int {
        // make sure we allocated the vertices array and that vertex exists
        if (vertices == null || index >= vertices!!.size) {
            PGraphics.showWarning("$NO_SUCH_VERTEX_ERROR ($index)", "getEmissive()")
            return emissiveColor
        }
        val r = (vertices!![index]!![PGraphics.ER] * 255).toInt()
        val g = (vertices!![index]!![PGraphics.EG] * 255).toInt()
        val b = (vertices!![index]!![PGraphics.EB] * 255).toInt()
        return -0x1000000 or (r shl 16) or (g shl 8) or b
    }

    open fun setEmissive(emissive: Int) {
        if (openShape) {
            PGraphics.showWarning(INSIDE_BEGIN_END_ERROR, "setEmissive()")
            return
        }
        emissiveColor = emissive
        if (vertices != null) {
            for (i in vertices!!.indices) {
                setEmissive(i, emissive)
            }
        }
    }

    open fun setEmissive(index: Int, emissive: Int) {
        if (openShape) {
            PGraphics.showWarning(INSIDE_BEGIN_END_ERROR, "setEmissive()")
            return
        }

        // make sure we allocated the vertices array and that vertex exists
        if (vertices == null ||
                index >= vertices!!.size) {
            PGraphics.showWarning("$NO_SUCH_VERTEX_ERROR ($index)", "setEmissive()")
            return
        }
        vertices!![index]!![PGraphics.ER] = (emissive shr 16 and 0xFF) / 255.0f
        vertices!![index]!![PGraphics.EG] = (emissive shr 8 and 0xFF) / 255.0f
        vertices!![index]!![PGraphics.EB] = (emissive shr 0 and 0xFF) / 255.0f
    }

    open fun getShininess(index: Int): Float {
        // make sure we allocated the vertices array and that vertex exists
        if (vertices == null ||
                index >= vertices!!.size) {
            PGraphics.showWarning("$NO_SUCH_VERTEX_ERROR ($index)", "getShininess()")
            return mshininess
        }
        return vertices!![index]!![PGraphics.SHINE]
    }

    open fun setShininess(shine: Float) {
        if (openShape) {
            PGraphics.showWarning(INSIDE_BEGIN_END_ERROR, "setShininess()")
            return
        }
        mshininess = shine
        if (vertices != null) {
            for (i in vertices!!.indices) {
                setShininess(i, shine)
            }
        }
    }

    open fun setShininess(index: Int, shine: Float) {
        if (openShape) {
            PGraphics.showWarning(INSIDE_BEGIN_END_ERROR, "setShininess()")
            return
        }

        // make sure we allocated the vertices array and that vertex exists
        if (vertices == null ||
                index >= vertices!!.size) {
            PGraphics.showWarning("$NO_SUCH_VERTEX_ERROR ($index)", "setShininess()")
            return
        }
        vertices!![index]!![PGraphics.SHINE] = shine
    }

    open fun getVertexCodes(): IntArray? {
        if (mvertexCodes == null) {
            return null
        }
        if (mvertexCodes!!.size != mvertexCodeCount) {
            mvertexCodes = PApplet.subset(mvertexCodes, 0, mvertexCodeCount)
        }
        return mvertexCodes
    }

    open fun getVertexCodeCount(): Int {
        return mvertexCodeCount
    }

    /**
     * One of VERTEX, BEZIER_VERTEX, CURVE_VERTEX, or BREAK.
     */
    open fun getVertexCode(index: Int): Int {
        return mvertexCodes!![index]
    }

    fun isClosed(): Boolean {
        return close
    }
    // . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . .
    /**
     * Return true if this x, y coordinate is part of this shape. Only works
     * with PATH shapes or GROUP shapes that contain other GROUPs or PATHs.
     */
    open fun contains(x: Float, y: Float): Boolean {
        return if (family == PATH) {
            val p = PVector(x, y)
            if (matrix != null) {
                // apply the inverse transformation matrix to the point coordinates
                val inverseCoords = matrix!!.get()
                // TODO why is this called twice? [fry 190724]
                // commit was https://github.com/processing/processing/commit/027fc7a4f8e8d0a435366eae754304eea282512a
                inverseCoords!!.invert() // maybe cache this?
                inverseCoords.invert() // maybe cache this?
                inverseCoords.mult(PVector(x, y), p)
            }

            // http://www.ecse.rpi.edu/Homepages/wrf/Research/Short_Notes/pnpoly.html
            var c = false
            var i = 0
            var j = mvertexCount - 1
            while (i < mvertexCount) {
                if (vertices!![i]!![PConstants.Y] > p.y != vertices!![j]!![PConstants.Y] > p.y &&
                        p.x <
                        (vertices!![j]!![PConstants.X] - vertices!![i]!![PConstants.X]) *
                        (y - vertices!![i]!![PConstants.Y]) /
                        (vertices!![j]!![1] - vertices!![i]!![PConstants.Y]) +
                        vertices!![i]!![PConstants.X]) {
                    c = !c
                }
                j = i++
            }
            c
        } else if (family == PConstants.GROUP) {
            // If this is a group, loop through children until we find one that
            // contains the supplied coordinates. If a child does not support
            // contains() throw a warning and continue.
            for (i in 0 until childCount) {
                if (mchildren!![i]!!.contains(x, y)) return true
            }
            false
        } else {
            // https://github.com/processing/processing/issues/1280
            throw IllegalArgumentException("The contains() method is only implemented for paths.")
        }
    }
    // . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . .
    // translate, rotate, scale, apply (no push/pop)
    //   these each call matrix.translate, etc
    // if matrix is null when one is called,
    //   it is created and set to identity
    /**
     * ( begin auto-generated from PShape_translate.xml )
     *
     * Specifies an amount to displace the shape. The **x** parameter
     * specifies left/right translation, the **y** parameter specifies
     * up/down translation, and the **z** parameter specifies translations
     * toward/away from the screen. Subsequent calls to the method accumulates
     * the effect. For example, calling **translate(50, 0)** and then
     * **translate(20, 0)** is the same as **translate(70, 0)**. This
     * transformation is applied directly to the shape, it's not refreshed each
     * time **draw()** is run.
     * <br></br><br></br>
     * Using this method with the **z** parameter requires using the P3D
     * parameter in combination with size.
     *
     * ( end auto-generated )
     * @webref pshape:method
     * @usage web_application
     * @brief Displaces the shape
     * @param x left/right translation
     * @param y up/down translation
     * @see PShape.rotate
     * @see PShape.scale
     * @see PShape.resetMatrix
     */
    open fun translate(x: Float, y: Float) {
        checkMatrix(2)
        matrix!!.translate(x, y)
    }

    /**
     * @param z forward/back translation
     */
    open fun translate(x: Float, y: Float, z: Float) {
        checkMatrix(3)
        matrix!!.translate(x, y, z)
    }

    /**
     * ( begin auto-generated from PShape_rotateX.xml )
     *
     * Rotates a shape around the x-axis the amount specified by the
     * **angle** parameter. Angles should be specified in radians (values
     * from 0 to TWO_PI) or converted to radians with the **radians()** method.
     * <br></br><br></br>
     * Shapes are always rotated around the upper-left corner of their bounding
     * box. Positive numbers rotate objects in a clockwise direction.
     * Subsequent calls to the method accumulates the effect. For example,
     * calling **rotateX(HALF_PI)** and then **rotateX(HALF_PI)** is the
     * same as **rotateX(PI)**. This transformation is applied directly to
     * the shape, it's not refreshed each time **draw()** is run.
     * <br></br><br></br>
     * This method requires a 3D renderer. You need to use P3D as a third
     * parameter for the **size()** function as shown in the example above.
     *
     * ( end auto-generated )
     * @webref pshape:method
     * @usage web_application
     * @brief Rotates the shape around the x-axis
     * @param angle angle of rotation specified in radians
     * @see PShape.rotate
     * @see PShape.rotateY
     * @see PShape.rotateZ
     * @see PShape.scale
     * @see PShape.translate
     * @see PShape.resetMatrix
     */
    open fun rotateX(angle: Float) {
        rotate(angle, 1f, 0f, 0f)
    }

    /**
     * ( begin auto-generated from PShape_rotateY.xml )
     *
     * Rotates a shape around the y-axis the amount specified by the
     * **angle** parameter. Angles should be specified in radians (values
     * from 0 to TWO_PI) or converted to radians with the **radians()** method.
     * <br></br><br></br>
     * Shapes are always rotated around the upper-left corner of their bounding
     * box. Positive numbers rotate objects in a clockwise direction.
     * Subsequent calls to the method accumulates the effect. For example,
     * calling **rotateY(HALF_PI)** and then **rotateY(HALF_PI)** is the
     * same as **rotateY(PI)**. This transformation is applied directly to
     * the shape, it's not refreshed each time **draw()** is run.
     * <br></br><br></br>
     * This method requires a 3D renderer. You need to use P3D as a third
     * parameter for the **size()** function as shown in the example above.
     *
     * ( end auto-generated )
     *
     * @webref pshape:method
     * @usage web_application
     * @brief Rotates the shape around the y-axis
     * @param angle angle of rotation specified in radians
     * @see PShape.rotate
     * @see PShape.rotateX
     * @see PShape.rotateZ
     * @see PShape.scale
     * @see PShape.translate
     * @see PShape.resetMatrix
     */
    open fun rotateY(angle: Float) {
        rotate(angle, 0f, 1f, 0f)
    }

    /**
     * ( begin auto-generated from PShape_rotateZ.xml )
     *
     * Rotates a shape around the z-axis the amount specified by the
     * **angle** parameter. Angles should be specified in radians (values
     * from 0 to TWO_PI) or converted to radians with the **radians()** method.
     * <br></br><br></br>
     * Shapes are always rotated around the upper-left corner of their bounding
     * box. Positive numbers rotate objects in a clockwise direction.
     * Subsequent calls to the method accumulates the effect. For example,
     * calling **rotateZ(HALF_PI)** and then **rotateZ(HALF_PI)** is the
     * same as **rotateZ(PI)**. This transformation is applied directly to
     * the shape, it's not refreshed each time **draw()** is run.
     * <br></br><br></br>
     * This method requires a 3D renderer. You need to use P3D as a third
     * parameter for the **size()** function as shown in the example above.
     *
     * ( end auto-generated )
     * @webref pshape:method
     * @usage web_application
     * @brief Rotates the shape around the z-axis
     * @param angle angle of rotation specified in radians
     * @see PShape.rotate
     * @see PShape.rotateX
     * @see PShape.rotateY
     * @see PShape.scale
     * @see PShape.translate
     * @see PShape.resetMatrix
     */
    open fun rotateZ(angle: Float) {
        rotate(angle, 0f, 0f, 1f)
    }

    /**
     * ( begin auto-generated from PShape_rotate.xml )
     *
     * Rotates a shape the amount specified by the **angle** parameter.
     * Angles should be specified in radians (values from 0 to TWO_PI) or
     * converted to radians with the **radians()** method.
     * <br></br><br></br>
     * Shapes are always rotated around the upper-left corner of their bounding
     * box. Positive numbers rotate objects in a clockwise direction.
     * Transformations apply to everything that happens after and subsequent
     * calls to the method accumulates the effect. For example, calling
     * **rotate(HALF_PI)** and then **rotate(HALF_PI)** is the same as
     * **rotate(PI)**. This transformation is applied directly to the shape,
     * it's not refreshed each time **draw()** is run.
     *
     * ( end auto-generated )
     * @webref pshape:method
     * @usage web_application
     * @brief Rotates the shape
     * @param angle angle of rotation specified in radians
     * @see PShape.rotateX
     * @see PShape.rotateY
     * @see PShape.rotateZ
     * @see PShape.scale
     * @see PShape.translate
     * @see PShape.resetMatrix
     */
    open fun rotate(angle: Float) {
        checkMatrix(2) // at least 2...
        matrix!!.rotate(angle)
    }

    /**
     * @nowebref
     */
    open fun rotate(angle: Float, v0: Float, v1: Float, v2: Float) {
        var v0 = v0
        var v1 = v1
        var v2 = v2
        checkMatrix(3)
        val norm2 = v0 * v0 + v1 * v1 + v2 * v2
        if (Math.abs(norm2 - 1) > PConstants.EPSILON) {
            // The rotation vector is not normalized.
            val norm = PApplet.sqrt(norm2)
            v0 /= norm
            v1 /= norm
            v2 /= norm
        }
        matrix!!.rotate(angle, v0, v1, v2)
    }
    //
    /**
     * ( begin auto-generated from PShape_scale.xml )
     *
     * Increases or decreases the size of a shape by expanding and contracting
     * vertices. Shapes always scale from the relative origin of their bounding
     * box. Scale values are specified as decimal percentages. For example, the
     * method call **scale(2.0)** increases the dimension of a shape by
     * 200%. Subsequent calls to the method multiply the effect. For example,
     * calling **scale(2.0)** and then **scale(1.5)** is the same as
     * **scale(3.0)**. This transformation is applied directly to the shape,
     * it's not refreshed each time **draw()** is run.
     * <br></br><br></br>
     * Using this method with the **z** parameter requires using the P3D
     * parameter in combination with size.
     *
     * ( end auto-generated )
     * @webref pshape:method
     * @usage web_application
     * @brief Increases and decreases the size of a shape
     * @param s percentate to scale the object
     * @see PShape.rotate
     * @see PShape.translate
     * @see PShape.resetMatrix
     */
    open fun scale(s: Float) {
        checkMatrix(2) // at least 2...
        matrix!!.scale(s)
    }

    open fun scale(x: Float, y: Float) {
        checkMatrix(2)
        matrix!!.scale(x, y)
    }

    /**
     * @param x percentage to scale the object in the x-axis
     * @param y percentage to scale the object in the y-axis
     * @param z percentage to scale the object in the z-axis
     */
    open fun scale(x: Float, y: Float, z: Float) {
        checkMatrix(3)
        matrix!!.scale(x, y, z)
    }
    //
    /**
     * ( begin auto-generated from PShape_resetMatrix.xml )
     *
     * Replaces the current matrix of a shape with the identity matrix. The
     * equivalent function in OpenGL is glLoadIdentity().
     *
     * ( end auto-generated )
     * @webref pshape:method
     * @brief Replaces the current matrix of a shape with the identity matrix
     * @usage web_application
     * @see PShape.rotate
     * @see PShape.scale
     * @see PShape.translate
     */
    open fun resetMatrix() {
        checkMatrix(2)
        matrix!!.reset()
    }

    fun applyMatrix(source: PMatrix?) {
        if (source is PMatrix2D) {
            applyMatrix(source)
        } else if (source is PMatrix3D) {
            applyMatrix(source)
        }
    }

    open fun applyMatrix(source: PMatrix2D) {
        applyMatrix(source.m00, source.m01, 0f, source.m02,
                source.m10, source.m11, 0f, source.m12, 0f, 0f, 1f, 0f, 0f, 0f, 0f, 1f)
    }

    open fun applyMatrix(n00: Float, n01: Float, n02: Float,
                         n10: Float, n11: Float, n12: Float) {
        checkMatrix(2)
        matrix!!.apply(n00, n01, n02,
                n10, n11, n12)
    }

    fun applyMatrix(source: PMatrix3D) {
        applyMatrix(source.m00, source.m01, source.m02, source.m03,
                source.m10, source.m11, source.m12, source.m13,
                source.m20, source.m21, source.m22, source.m23,
                source.m30, source.m31, source.m32, source.m33)
    }

    open fun applyMatrix(n00: Float, n01: Float, n02: Float, n03: Float,
                         n10: Float, n11: Float, n12: Float, n13: Float,
                         n20: Float, n21: Float, n22: Float, n23: Float,
                         n30: Float, n31: Float, n32: Float, n33: Float) {
        checkMatrix(3)
        matrix!!.apply(n00, n01, n02, n03,
                n10, n11, n12, n13,
                n20, n21, n22, n23,
                n30, n31, n32, n33)
    }
    //
    /**
     * Make sure that the shape's matrix is 1) not null, and 2) has a matrix
     * that can handle *at least* the specified number of dimensions.
     */
    protected open fun checkMatrix(dimensions: Int) {
        if (matrix == null) {
            matrix = if (dimensions == 2) {
                PMatrix2D()
            } else {
                PMatrix3D()
            }
        } else if (dimensions == 3 && matrix is PMatrix2D) {
            // time for an upgrayedd for a double dose of my pimpin'
            matrix = PMatrix3D(matrix)
        }
    }

    /**
     * @param max range for all color elements
     */
    fun colorMode(mode: Int, max: Float) {
        colorMode(mode, max, max, max, max)
    }
    /**
     * @param maxA range for the alpha
     */
    // . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . .
    /**
     * Center the shape based on its bounding box. Can't assume
     * that the bounding box is 0, 0, width, height. Common case will be
     * opening a letter size document in Illustrator, and drawing something
     * in the middle, then reading it in as an svg file.
     * This will also need to flip the y axis (scale(1, -1)) in cases
     * like Adobe Illustrator where the coordinates start at the bottom.
     */
    //  public void center() {
    //  }
    /**
     * Set the pivot point for all transformations.
     */
    //  public void pivot(float x, float y) {
    //    px = x;
    //    py = y;
    //  }
    // . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . .
    /**
     * @param maxX range for the red or hue depending on the current color mode
     * @param maxY range for the green or saturation depending on the current color mode
     * @param maxZ range for the blue or brightness depending on the current color mode
     */
    fun colorMode(mode: Int,
                  maxX: Float, maxY: Float, maxZ: Float, maxA: Float) {
        colorMode = mode
        colorModeX = maxX // still needs to be set for hsb
        colorModeY = maxY
        colorModeZ = maxZ
        colorModeA = maxA

        // if color max values are all 1, then no need to scale
        colorModeScale = maxA != 1f || maxX != maxY || maxY != maxZ || maxZ != maxA

        // if color is rgb/0..255 this will make it easier for the
        // red() green() etc functions
        colorModeDefault = colorMode == PConstants.RGB &&
                colorModeA == 255f && colorModeX == 255f &&
                colorModeY == 255f && colorModeZ == 255f
    }

    protected fun colorCalc(rgb: Int) {
        if (rgb and -0x1000000 == 0 && rgb <= colorModeX) {
            colorCalc(rgb.toFloat())
        } else {
            colorCalcARGB(rgb, colorModeA)
        }
    }

    protected fun colorCalc(rgb: Int, alpha: Float) {
        if (rgb and -0x1000000 == 0 && rgb <= colorModeX) {  // see above
            colorCalc(rgb.toFloat(), alpha)
        } else {
            colorCalcARGB(rgb, alpha)
        }
    }

    protected fun colorCalc(gray: Float, alpha: Float = colorModeA) {
        var gray = gray
        var alpha = alpha
        if (gray > colorModeX) gray = colorModeX
        if (alpha > colorModeA) alpha = colorModeA
        if (gray < 0) gray = 0f
        if (alpha < 0) alpha = 0f
        calcR = if (colorModeScale) gray / colorModeX else gray
        calcG = calcR
        calcB = calcR
        calcA = if (colorModeScale) alpha / colorModeA else alpha
        calcRi = (calcR * 255).toInt()
        calcGi = (calcG * 255).toInt()
        calcBi = (calcB * 255).toInt()
        calcAi = (calcA * 255).toInt()
        calcColor = calcAi shl 24 or (calcRi shl 16) or (calcGi shl 8) or calcBi
        calcAlpha = calcAi != 255
    }

    protected fun colorCalc(x: Float, y: Float, z: Float, a: Float = colorModeA) {
        var x = x
        var y = y
        var z = z
        var a = a
        if (x > colorModeX) x = colorModeX
        if (y > colorModeY) y = colorModeY
        if (z > colorModeZ) z = colorModeZ
        if (a > colorModeA) a = colorModeA
        if (x < 0) x = 0f
        if (y < 0) y = 0f
        if (z < 0) z = 0f
        if (a < 0) a = 0f
        when (colorMode) {
            PConstants.RGB -> if (colorModeScale) {
                calcR = x / colorModeX
                calcG = y / colorModeY
                calcB = z / colorModeZ
                calcA = a / colorModeA
            } else {
                calcR = x
                calcG = y
                calcB = z
                calcA = a
            }
            PConstants.HSB -> {
                x /= colorModeX // h
                y /= colorModeY // s
                z /= colorModeZ // b
                calcA = if (colorModeScale) a / colorModeA else a
                if (y == 0f) {  // saturation == 0
                    calcB = z
                    calcG = calcB
                    calcR = calcG
                } else {
                    val which = (x - x.toInt()) * 6.0f
                    val f = which - which.toInt()
                    val p = z * (1.0f - y)
                    val q = z * (1.0f - y * f)
                    val t = z * (1.0f - y * (1.0f - f))
                    when (which.toInt()) {
                        0 -> {
                            calcR = z
                            calcG = t
                            calcB = p
                        }
                        1 -> {
                            calcR = q
                            calcG = z
                            calcB = p
                        }
                        2 -> {
                            calcR = p
                            calcG = z
                            calcB = t
                        }
                        3 -> {
                            calcR = p
                            calcG = q
                            calcB = z
                        }
                        4 -> {
                            calcR = t
                            calcG = p
                            calcB = z
                        }
                        5 -> {
                            calcR = z
                            calcG = p
                            calcB = q
                        }
                    }
                }
            }
        }
        calcRi = (255 * calcR).toInt()
        calcGi = (255 * calcG).toInt()
        calcBi = (255 * calcB).toInt()
        calcAi = (255 * calcA).toInt()
        calcColor = calcAi shl 24 or (calcRi shl 16) or (calcGi shl 8) or calcBi
        calcAlpha = calcAi != 255
    }

    protected fun colorCalcARGB(argb: Int, alpha: Float) {
        if (alpha == colorModeA) {
            calcAi = argb shr 24 and 0xff
            calcColor = argb
        } else {
            calcAi = ((argb shr 24 and 0xff) * (alpha / colorModeA)).toInt()
            calcColor = calcAi shl 24 or (argb and 0xFFFFFF)
        }
        calcRi = argb shr 16 and 0xff
        calcGi = argb shr 8 and 0xff
        calcBi = argb and 0xff
        calcA = calcAi / 255.0f
        calcR = calcRi / 255.0f
        calcG = calcGi / 255.0f
        calcB = calcBi / 255.0f
        calcAlpha = calcAi != 255
    }

    companion object {
        //  /** Generic, only draws its child objects. */
        //  static public final int GROUP = 0;
        // GROUP now inherited from PConstants, and is still zero
        // These constants were updated in 3.0b6 so that they could be distinguished
        // from others in PConstants and improve how some typos were handled.
        // https://github.com/processing/processing/issues/3776
        /** A line, ellipse, arc, image, etc.  */
        const val PRIMITIVE = 101

        /** A series of vertex, curveVertex, and bezierVertex calls.  */
        const val PATH = 102

        /** Collections of vertices created with beginShape().  */
        const val GEOMETRY = 103
        const val OUTSIDE_BEGIN_END_ERROR = "%1\$s can only be called between beginShape() and endShape()"
        const val INSIDE_BEGIN_END_ERROR = "%1\$s can only be called outside beginShape() and endShape()"
        const val NO_SUCH_VERTEX_ERROR = "%1\$s vertex index does not exist"
        const val NO_VERTICES_ERROR = "getVertexCount() only works with PATH or GEOMETRY shapes"
        const val NOT_A_SIMPLE_VERTEX = "%1\$s can not be called on quadratic or bezier vertices"
        const val PER_VERTEX_UNSUPPORTED = "This renderer does not support %1\$s for individual vertices"

        ////////////////////////////////////////////////////////////////////////
        //
        // Shape copy
        // TODO unapproved
        @JvmStatic
        protected fun createShape(parent: PApplet, src: PShape?): PShape? {
            var dest: PShape? = null
            if (src!!.family == PConstants.GROUP) {
                dest = parent.createShape(PConstants.GROUP)
                copyGroup(parent, src, dest)
            } else if (src.family == PRIMITIVE) {
                dest = parent.createShape(src.kind, *src!!.mparams!!)
                copyPrimitive(src, dest)
            } else if (src.family == GEOMETRY) {
                dest = parent.createShape(src.kind)
                copyGeometry(src, dest)
            } else if (src.family == PATH) {
                dest = parent.createShape(PATH)
                copyPath(src, dest)
            }
            dest!!.name = src.name
            return dest
        }

        // TODO unapproved
        @JvmStatic
        protected fun copyGroup(parent: PApplet, src: PShape?, dest: PShape?) {
            copyMatrix(src, dest)
            copyStyles(src, dest)
            copyImage(src, dest)
            for (i in 0 until src!!.childCount) {
                val c = createShape(parent, src.mchildren!![i])
                dest!!.addChild(c)
            }
        }

        // TODO unapproved
        @JvmStatic
        protected fun copyPrimitive(src: PShape?, dest: PShape?) {
            copyMatrix(src, dest)
            copyStyles(src, dest)
            copyImage(src, dest)
        }

        // TODO unapproved
        @JvmStatic
        protected fun copyGeometry(src: PShape?, dest: PShape?) {
            dest!!.beginShape(src!!.kind)
            copyMatrix(src, dest)
            copyStyles(src, dest)
            copyImage(src, dest)
            if (src.style) {
                for (i in 0 until src.mvertexCount) {
                    val vert = src.vertices!![i]
                    dest.fill((vert!![PGraphics.A] * 255).toInt() shl 24 or (
                            (vert[PGraphics.R] * 255).toInt() shl 16) or (
                            (vert[PGraphics.G] * 255).toInt() shl 8) or
                            (vert[PGraphics.B] * 255).toInt())

                    // Do we need to copy these as well?
//        dest.ambient(vert[PGraphics.AR] * 255, vert[PGraphics.AG] * 255, vert[PGraphics.AB] * 255);
//        dest.specular(vert[PGraphics.SPR] * 255, vert[PGraphics.SPG] * 255, vert[PGraphics.SPB] * 255);
//        dest.emissive(vert[PGraphics.ER] * 255, vert[PGraphics.EG] * 255, vert[PGraphics.EB] * 255);
//        dest.shininess(vert[PGraphics.SHINE]);
                    if (0 < PApplet.dist(vert[PGraphics.NX],
                                    vert[PGraphics.NY],
                                    vert[PGraphics.NZ], 0f, 0f, 0f)) {
                        dest.normal(vert[PGraphics.NX],
                                vert[PGraphics.NY],
                                vert[PGraphics.NZ])
                    }
                    dest.vertex(vert[PConstants.X], vert[PConstants.Y], vert[PConstants.Z],
                            vert[PGraphics.U],
                            vert[PGraphics.V])
                }
            } else {
                for (i in 0 until src.mvertexCount) {
                    val vert = src.vertices!![i]
                    if (vert!![PConstants.Z] == 0F) {
                        dest.vertex(vert[PConstants.X], vert[PConstants.Y])
                    } else {
                        dest.vertex(vert[PConstants.X], vert[PConstants.Y], vert[PConstants.Z])
                    }
                }
            }
            dest.endShape()
        }

        // TODO unapproved
        @JvmStatic
        protected fun copyPath(src: PShape?, dest: PShape?) {
            copyMatrix(src, dest)
            copyStyles(src, dest)
            copyImage(src, dest)
            dest!!.close = src!!.close
            dest.setPath(src.mvertexCount, src.vertices, src.mvertexCodeCount, src.mvertexCodes)
        }

        // TODO unapproved
        @JvmStatic
        protected fun copyMatrix(src: PShape?, dest: PShape?) {
            if (src!!.matrix != null) {
                dest!!.applyMatrix(src.matrix)
            }
        }

        // TODO unapproved
        @JvmStatic
        protected fun copyStyles(src: PShape?, dest: PShape?) {
            dest!!.ellipseMode = src!!.ellipseMode
            dest.rectMode = src.rectMode
            if (src.mstroke) {
                dest.mstroke = true
                dest.strokeColor = src.strokeColor
                dest.mstrokeWeight = src.mstrokeWeight
                dest.mstrokeCap = src.mstrokeCap
                dest.mstrokeJoin = src.mstrokeJoin
            } else {
                dest.mstroke = false
            }
            if (src.psfill) {
                dest.psfill = true
                dest.fillColor = src.fillColor
            } else {
                dest.psfill = false
            }
        }

        // TODO unapproved
        @JvmStatic
        protected fun copyImage(src: PShape?, dest: PShape?) {
            if (src!!.image != null) {
                dest!!.texture(src.image)
            }
        }

        // Replacement for DatatypeConverter
        // https://github.com/hierynomus/sshj/issues/366#issue-261511648
        @Throws(IllegalArgumentException::class)
        private fun parseHexBinary(s: String): ByteArray {
            var s = s ?: return ByteArray(0)
            s = s.trim { it <= ' ' }
            val length = s.length
            require(length % 2 == 0) { "Invalid hex string length." }
            val result = ByteArray(length / 2)
            var i = 0
            while (i < length) {
                result[i / 2] = s.substring(i, i + 2).toInt(16).toByte()
                i += 2
            }
            return result
        }
    }
}