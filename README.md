<h2>Introduction</h2>
<p>
    I have always been fascinated by space and when I learned the basic physical laws that describes the dynamics of bodies I was surprised by how simple they were! A few simple equations can be used to explain the movement of projectiles, planets etc.
</p>
<p>
    Many astronomical and astrophysical phenomena can be simulated using <a href="https://en.wikipedia.org/wiki/N-body_simulation" target="_blank">N-body simulation</a> which simulates a dynamical system of particles, usually under the influence of physical forces, such as gravity. N-body simulation is also often used in game-physics to make objects move around, accelerating etc.
</p>
<p>
    When you have a large number of particles, all interacting with each other, it can be hard (or impossible) to formulate analytical mathematical solutions that describes the state of the system for a given point in time. Instead you can do the following:
        <ol>
            <li>Define an initial state for all objects in the system (e.g position and speed of all objects)</li>
            <li>Calculate all forces for all objects in the systems for the current state</li>
            <li>Calculate a new state given that we apply the forces from step 2 above to the system for a given amount of time &Delta;T</li>
            <li>Repeat step 2</li>
        </ol>
</p>
<p>
    
        The accuracy of the simulation will depend on how small we make &Delta;T, smaller &Delta;T implies better accuracy but longer computational time.
</p>
<p>
    I have implemented a simple N-body physics engine that can calculate movements of objects caused by gravitational forces. I use the implementation to simulate the movement of planets in our solar system. The simulation is initialized using real physical data about the planets positions and velocities and then calculates changes due to the affect of gravitation. The results are then  visualized using JavaFX.
</p>
<p>
    
</p>

<h2>Theoretical background</h2>
<p>
    When Newton described the laws of motion in his monumental <i>Principia Mathematica</i> (1687) it was a remarkable achievement. It states that in principle all movement of objects can be described using the following:.
</p>
<p>
    <b>Newtons first law</b></br></br>
    <i>"Every body persists in its state of being at rest or of moving uniformly straight forward, except insofar as it is compelled to change its state by force impressed."</i></br></br>
    This states that an object will either be at rest or move in a straight path if no external forces are applied to it. If you throw a golf ball in empty space it will continue forever unless there is some force acting upon it. The reason a golf boll don't vanish when you trow it at earth is because there are several forces acting upon it, e.g gravity and air resistance.
</p>
<p>
    <b>Newtons second law</b></br></br>
    <i>"The alteration of motion is ever proportional to the motive force impress'd; and is made in the direction of the right line in which that force is impress'd."</i></br></br>
    <i>Equation: F = m * a or a = F/m</i></br></br>
     where F is the net force applied, m is the mass of the body, and a is the body's acceleration.
    </br></br>
    This states that when you apply a force to a body it will accelerate proportional to its mass. Massive bodies requires more force to accelerate.
    
    
</p>
<p>
    <b>Newtons gravitational law</b></br></br>
    <i>Equation: F = G (m1 * m2) / r²</i></br>
    
    <ul>G is the gravitational constant (6.674×10−11 N(m/kg)2)</ul>
        <ul>m1 is the first mass</ul>
        <ul>m2 is the second mass</ul>
        <ul>r is the distance between the centers of the masses.</ul>
    </ul>
</p>
<p>
    This states all objects attracts each other by a gravitational force that is proportional to the objects mass and the distance between them.
</p>
<p>
    So to summarize:
    <ul>
        <li>An object will not change its movement unless some force is acting on it. Note that this also implies that an object will continue to move in a straight line if no force is acting on it and the object was not at rest before.
        </li>
        <li>Gravitation is a force that affects all objects. In space gravitation is the dominating force so the movement of planets are in principal only dependent on this force (no air resistance in space...)</li>
    </ul>
</p>
<p>
    Also note that both Newtons second law and his gravitational law denotes vectors, ie forces and accelerations have directions which can be described using motion vectors.
</p>
<h2>Implementation</h2>
<p>
    The application flow is the following:
     <ol>
            <li>Define an initial state for all objects (planets) in the system including location, speed and mass. Initial data was fetched from  <a href="https://ssd.jpl.nasa.gov/horizons.cgi" target="_blank">NASA:s page for solar system dynamics</a> using time =  2017-May-02 00:00:00.0000.</li>
            <li>Calculate all forces for all objects in the systems using Newtons law for gravitation (F = G (m1 * m2) / r²).</li>
            <li>Calculate the acceleration for all objects in the systems using Newtons second law (F = m * a).</li>
            <li>Calculate the change in speed for an object given that the acceleration calculated in the previous step was applied for given time slice (&Delta;T)</li>
            <li>Calculate the change in location for an object given that the speed calculated in the previous step was applied for given &Delta;T (s = v * t)</li>
            <li>Update GUI</li>
            <li>Repeat from step 2</li>
        </ol>
</p>

<p><b>Vectors</b></p>
<p>
    At heart of the implementation is a three dimensional vector class (<i>Vector3D</i>) and the class <i>Body</i>. The body class contains three vectors describing an objects location, velocity and acceleration:
</p>
<p>
    <ul>
    <li>Location is described using x, y and z coordinates (meters).</li>
    <li>Velocity is described using velocity in x-direction, y-direction and z-direction (meters/second).</li>
    <li>Acceleration is described using acceleration in x-direction, y-direction and z-direction (meters/second²).</li>
    </ul>
</p>
<p>
    The vectors described are not the standard Vector type in java, instead it is a custom implementation to support <a href="https://en.wikipedia.org/wiki/Vector_algebra" target="_blank">vector algebra</a> which makes it possible to do mathematical operations like multiplication and addition on vectors.
</p>
<p><b>Graphical interface</b></p>
<p>
    The gui is a minimal JavaFX application the is used to draw circles that represents the bodies the model. The interface also allows the user to navigate by pressing and dragging the mouse and by using the scroll wheel for zooming.
</p>

<h3>Final remarks</h3>
<p>
    For me it was a pretty cool the first time I ran the simulation successfully and I could see all planets orbiting the Sun at expected rates etc. You can see that some planets like Mercury have strongly elliptic orbits and that the velocity of planets increases when they are closer to the Sun. All this is achieved by using two simple equations and a bit of high school math.
</p>
<p>
    The complete source for the application can be downloaded from <a href="https://github.com/milin44/nbody" target="_blank">GitHub</a>
</p>




