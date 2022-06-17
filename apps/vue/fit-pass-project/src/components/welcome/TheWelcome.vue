<template>
  <div class="welcome-section-membership">
    <div class="inner">
      <div class="inner-description">
        <h1>Train</h1>
        <p>
          in best gyms, fitness studios and
          sport centers around Serbia,
          with only one membership!
        </p>
      </div>
      <div class="inner-price">
        <h1>
          Just For 2900 RSD.
        </h1>
      </div>
      <div class="inner-membership-buttons">
        <button>Buy FitPass Membership</button>
      </div>
    </div>
  </div>
</template>

<script setup>

</script>

<script>
export default {
  name: "TheWelcome"
}
</script>

<style scoped>
.welcome-section-membership {
  height: 60vh;
  background: url("src/assets/imgs/training.jpg") no-repeat;
  background-size: cover;
  color: white;
  padding: 10%;
  font-family: Calibri,sans-serif;

}
.welcome-section-membership .inner {
  width: 30%;
}

.welcome-section-membership .inner-description {
  font-weight: bold;
  color: #fcf8f7
}
.welcome-section-membership .inner-description h1 {
  font-size: 2.5rem;
}
.welcome-section-membership .inner-description p {
  font-size: 1.5rem;
}
.welcome-section-membership .inner-price {
  color: #ff7810;
  font-size: 1.5rem;
}
.welcome-section-membership .inner-membership-buttons button {
  font-family: Calibri,sans-serif;
  font-size: 1.5rem;
  font-weight: bold;
  background: #ff7810;
  color: white;
  outline: white;
  border: none;
  border-radius: 5px;
  padding: 10px;
  cursor: pointer;
  background: linear-gradient(to right, black 50%, #ff7810 50%);
  background-size: 200% 100%;
  background-position: right bottom;
  transition: all .5s ease-out;
}

.welcome-section-membership .inner-membership-buttons button:hover {
  background-position: left bottom;
}
.welcome-section-membership .inner-membership-buttons span {
  font-weight: bold;
  font-size: 2rem;
}


.welcome-section-membership .icon-big {
  height: 4rem;
  width: 4rem;
}

</style>


