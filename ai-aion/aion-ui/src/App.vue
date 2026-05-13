<template>
  <div id="app">
    <LogicForest />
  </div>
</template>

<script setup lang="ts">
import LogicForest from './components/LogicForest.vue';
</script>

<style>
body {
  margin: 0;
  padding: 0;
  background-color: #050505;
  color: white;
  overflow: hidden;
}
#app {
  width: 100vw;
  height: 100vh;
}
</style>
