<template>
	<div>
    <!-- Wrapper -->
    <div id="wrapper" class="divided">

      <!-- Banner -->
      <section class="banner style1 orient-left content-align-left image-position-right fullscreen onload-image-fade-in onload-content-fade-right">
        <div class="content">
          <h1>Story</h1>
          <p class="major">A (modular, highly tweakable) responsive blog web. This is for Bernie_fang’s training assignments</p>
          <p>This is a <strong>Banner</strong> element, I personally think it looks nice, so these pictures have not been modified.
            I am a photography enthusiast myself. When I saw this webpage, I decided to use it as my <strong>homepage</strong>,
            which is what you see now </p>
          <ul class="actions vertical">
            <li><a class="button big wide smooth-scroll-middle" @click="getStarted">Get Started</a></li>
          </ul>
        </div>
        <div class="image">
          <img src="~assets/img/index/banner.jpg" alt="" />
        </div>
      </section>

      <!-- Spotlight -->
      <section class="spotlight style1 orient-right content-align-left image-position-center onscroll-image-fade-in" id="first">
        <div class="content">
          <h2>Spotlight</h2>
          <p>This is a <strong>Spotlight</strong> element, and it's generally used &ndash; as its name implies &ndash; to spotlight a particular feature, subject, or pretty much whatever. You can customize its <span class="demo-controls">appearance with a number of modifiers</span>, as well as assign it an optional <code>onload</code> or <code>onscroll</code> transition modifier (<a href="#reference-spotlight">details</a>).</p>
          <ul class="actions vertical">
            <li><a href="#" class="button">Learn More</a></li>
          </ul>
        </div>
        <div class="image">
          <img src="~assets/img/index/spotlight01.jpg" alt="" />
        </div>
      </section>

      <!-- Spotlight -->
      <section class="spotlight style1 orient-left content-align-left image-position-center onscroll-image-fade-in">
        <div class="content">
          <h2>Spotlight</h2>
          <p>This is also a <strong>Spotlight</strong> element, and it's here because this demo would look a bit empty with just one spotlight. Like all spotlights, you can customize its <span class="demo-controls">appearance with a number of modifiers</span>, as well as assign it an optional <code>onload</code> or <code>onscroll</code> transition modifier (<a href="#reference-spotlight">details</a>).</p>
          <ul class="actions vertical">
            <li><a href="#" class="button">Learn More</a></li>
          </ul>
        </div>
        <div class="image">
          <img src="~assets/img/index/spotlight02.jpg" alt="" />
        </div>
      </section>

      <!-- Spotlight -->
      <section class="spotlight style1 orient-right content-align-left image-position-center onscroll-image-fade-in">
        <div class="content">
          <h2>Spotlight</h2>
          <p>And yes, this is another <strong>Spotlight</strong> element, and it's also here because I need to fill a bit of space. Naturally, like any other spotlight, you can customize its <span class="demo-controls">appearance with a number of modifiers</span>, as well as assign it an optional <code>onload</code> or <code>onscroll</code> transition modifier (<a href="#reference-spotlight">details</a>).</p>
          <ul class="actions vertical">
            <li><a href="#" class="button">Learn More</a></li>
          </ul>
        </div>
        <div class="image">
          <img src="~assets/img/index/spotlight03.jpg" alt="" />
        </div>
      </section>

      <!-- Additional Elements -->
      <section class="wrapper style1 align-center">
        <div class="inner">
          <h2>Additional Elements</h2>
          <p>Finally, here are some additional elements you <em>might</em> find useful (and yup, they look fine with any <span class="demo-controls">appearance</span>).</p>
          <div class="index align-left">

            <!-- Text -->
            <section>
              <header>
                <h3>Text</h3>
              </header>
              <div class="content">

                <p>This is <b>bold</b> and this is <strong>strong</strong>. This is <i>italic</i> and this is <em>emphasized</em>.
                  This is <sup>superscript</sup> text and this is <sub>subscript</sub> text.
                  This is <u>underlined</u> and this is code: <code>for (;;) { ... }</code>.
                  Finally, this is a <a href="#">link</a>.</p>

              </div>
            </section>

            <!-- Heading -->
            <section>
              <header>
                <h3>Headings</h3>
              </header>
              <div class="content">

                <h1>Heading Level 1</h1>
                <h2>Heading Level 2</h2>
                <h3>Heading Level 3</h3>
                <h4>Heading Level 4</h4>
                <h5>Heading Level 5</h5>
                <h6>Heading Level 6</h6>

                <hr />

                <header>
                  <h1>Heading with a Subtitle</h1>
                  <p>Lorem ipsum dolor sit amet nullam id egestas urna aliquam</p>
                </header>
                <p>Nunc lacinia ante nunc ac lobortis. Interdum adipiscing gravida odio porttitor sem non mi integer non faucibus ornare mi ut ante amet placerat aliquet. Volutpat eu sed ante lacinia sapien lorem accumsan varius montes viverra nibh in adipiscing blandit tempus accumsan.</p>

                <hr />

                <header>
                  <h2>Heading with a Subtitle</h2>
                  <p>Lorem ipsum dolor sit amet nullam id egestas urna aliquam</p>
                </header>
                <p>Nunc lacinia ante nunc ac lobortis. Interdum adipiscing gravida odio porttitor sem non mi integer non faucibus ornare mi ut ante amet placerat aliquet. Volutpat eu sed ante lacinia sapien lorem accumsan varius montes viverra nibh in adipiscing blandit tempus accumsan.</p>

                <hr />

                <header>
                  <h3>Heading with a Subtitle</h3>
                  <p>Lorem ipsum dolor sit amet nullam id egestas urna aliquam</p>
                </header>
                <p>Nunc lacinia ante nunc ac lobortis. Interdum adipiscing gravida odio porttitor sem non mi integer non faucibus ornare mi ut ante amet placerat aliquet. Volutpat eu sed ante lacinia sapien lorem accumsan varius montes viverra nibh in adipiscing blandit tempus accumsan.</p>

              </div>
            </section>

            <!-- Definition List -->
            <section>
              <header>
                <h3>Definition List</h3>
              </header>
              <div class="content">

                <dl>
                  <dt>Interdum adipiscing odio</dt>
                  <dd>
                    <p>Lorem ipsum dolor vestibulum ante ipsum primis in faucibus vestibulum. Blandit adipiscing eu felis iaculis volutpat ac adipiscing accumsan eu faucibus. Integer ac pellentesque praesent fringilla quis tincidunt felis sagittis eget tempus euismod. Ante ipsum primis vestibulum.</p>
                  </dd>
                  <dt>Non faucibus ornare mi ut ante</dt>
                  <dd>
                    <p>Lorem ipsum dolor vestibulum ante ipsum primis in faucibus vestibulum. Blandit adipiscing eu felis iaculis volutpat ac adipiscing accumsan eu faucibus. Integer ac pellentesque praesent fringilla quis tincidunt felis sagittis eget tempus euismod. Ante ipsum primis vestibulum.</p>
                  </dd>
                  <dt>Felis amet dolore viverra</dt>
                  <dd>
                    <p>Lorem ipsum dolor vestibulum ante ipsum primis in faucibus vestibulum. Blandit adipiscing eu felis iaculis volutpat ac adipiscing accumsan eu faucibus. Integer ac pellentesque praesent fringilla quis tincidunt felis sagittis eget tempus euismod. Ante ipsum primis vestibulum.</p>
                  </dd>
                </dl>

              </div>
            </section>

            <!-- Form -->
            <section>
              <header>
                <h3>Form</h3>
              </header>
              <div class="content">

                <form method="post" action="#">
                  <div class="field half first">
                    <label for="name">Name</label>
                    <input type="text" name="name" id="name" value="" />
                  </div>
                  <div class="field half">
                    <label for="email">Email</label>
                    <input type="email" name="email" id="email" value="" />
                  </div>
                  <div class="field">
                    <label for="department">Department</label>
                    <div class="select-wrapper">
                      <select name="department" id="department">
                        <option value="">- Category -</option>
                        <option value="1">Manufacturing</option>
                        <option value="2">Shipping</option>
                        <option value="3">Administration</option>
                        <option value="4">Human Resources</option>
                      </select>
                    </div>
                  </div>
                  <div class="field third first">
                    <input type="radio" id="priority-low" name="priority" checked />
                    <label for="priority-low">Low Priority</label>
                  </div>
                  <div class="field third">
                    <input type="radio" id="priority-normal" name="priority" />
                    <label for="priority-normal">Normal Priority</label>
                  </div>
                  <div class="field third">
                    <input type="radio" id="priority-high" name="priority" />
                    <label for="priority-high">High Priority</label>
                  </div>
                  <div class="field">
                    <label for="message">Message</label>
                    <textarea name="message" id="message" rows="6"></textarea>
                  </div>
                  <div class="field half first">
                    <input type="checkbox" id="copy" name="copy" />
                    <label for="copy">Email me a copy of this message</label>
                  </div>
                  <div class="field half">
                    <input type="checkbox" id="human" name="human" checked />
                    <label for="human">I am a human and not a robot</label>
                  </div>
                  <ul class="actions">
                    <li><input type="submit" name="submit" id="submit" value="Send Message" /></li>
                  </ul>
                </form>

              </div>
            </section>

          </div>
        </div>
      </section>


      <!-- Footer -->
      <footer class="wrapper style1 align-center">
        <div class="inner">
          <ul class="icons">
            <li><a href="#" class="icon style2 fa-twitter"><span class="label">Twitter</span></a></li>
            <li><a href="#" class="icon style2 fa-facebook"><span class="label">Facebook</span></a></li>
            <li><a href="#" class="icon style2 fa-instagram"><span class="label">Instagram</span></a></li>
            <li><a href="#" class="icon style2 fa-linkedin"><span class="label">LinkedIn</span></a></li>
            <li><a href="#" class="icon style2 fa-envelope"><span class="label">Email</span></a></li>
          </ul>
          <p>&copy; Untitled. Design: <a href="https://html5up.net">HTML5 UP</a>.</p>
        </div>
      </footer>

    </div>

  </div>
</template>

<script>
  export default {
    name: "Index",
    methods:{
      getStarted(){
        this.$router.push('/main');
      }
    }
  }
</script>

<style scoped>
  @import "~assets/css/index/css/main.css";
</style>
