<template>
  <div ref="forestContainer" class="logic-forest-container">
    <div class="overlay">
      <h1>AI-Aion: 3D 逻辑森林 (Logic Forest)</h1>
      <p>当前灵识节点数量: {{ nodeCount }}</p>
      <div class="legend">
        <span class="dot conservative"></span> 保守派
        <span class="dot radical"></span> 激进派
        <span class="dot observer"></span> 观察者
      </div>
    </div>
  </div>
</template>

<script setup lang="ts">
import { ref, onMounted, onUnmounted } from 'vue';
import * as THREE from 'three';

const forestContainer = ref<HTMLElement | null>(null);
const nodeCount = ref(500);

let scene: THREE.Scene;
let camera: THREE.PerspectiveCamera;
let renderer: THREE.WebGLRenderer;
let nodes: THREE.Points;

const initThree = () => {
  if (!forestContainer.value) return;

  // 1. 初始化场景
  scene = new THREE.Scene();
  scene.background = new THREE.Color(0x050505);

  // 2. 初始化相机
  camera = new THREE.PerspectiveCamera(75, window.innerWidth / window.innerHeight, 0.1, 1000);
  camera.position.z = 100;

  // 3. 初始化渲染器
  renderer = new THREE.WebGLRenderer({ antialias: true });
  renderer.setSize(window.innerWidth, window.innerHeight);
  forestContainer.value.appendChild(renderer.domElement);

  // 4. 创建逻辑森林节点 (粒子系统)
  const geometry = new THREE.BufferGeometry();
  const positions = [];
  const colors = [];

  for (let i = 0; i < nodeCount.value; i++) {
    // 随机分布位置
    positions.push((Math.random() - 0.5) * 200);
    positions.push((Math.random() - 0.5) * 200);
    positions.push((Math.random() - 0.5) * 200);

    // 根据性格分配颜色
    const colorType = Math.random();
    if (colorType < 0.3) {
      colors.push(0, 0.8, 1); // 蓝色 - 保守
    } else if (colorType < 0.6) {
      colors.push(1, 0.2, 0); // 红色 - 激进
    } else {
      colors.push(0.5, 1, 0.5); // 绿色 - 观察
    }
  }

  geometry.setAttribute('position', new THREE.Float32BufferAttribute(positions, 3));
  geometry.setAttribute('color', new THREE.Float32BufferAttribute(colors, 3));

  const material = new THREE.PointsMaterial({
    size: 2,
    vertexColors: true,
    transparent: true,
    opacity: 0.8,
    blending: THREE.AdditiveBlending
  });

  nodes = new THREE.Points(geometry, material);
  scene.add(nodes);

  // 动画循环
  const animate = () => {
    requestAnimationFrame(animate);
    nodes.rotation.y += 0.001;
    nodes.rotation.x += 0.0005;
    renderer.render(scene, camera);
  };
  animate();
};

onMounted(() => {
  initThree();
  window.addEventListener('resize', handleResize);
});

onUnmounted(() => {
  window.removeEventListener('resize', handleResize);
});

const handleResize = () => {
  if (!camera || !renderer) return;
  camera.aspect = window.innerWidth / window.innerHeight;
  camera.updateProjectionMatrix();
  renderer.setSize(window.innerWidth, window.innerHeight);
};
</script>

<style scoped>
.logic-forest-container {
  width: 100vw;
  height: 100vh;
  position: relative;
  overflow: hidden;
}

.overlay {
  position: absolute;
  top: 20px;
  left: 20px;
  color: #gold;
  color: rgba(255, 215, 0, 0.8);
  font-family: 'Inter', sans-serif;
  pointer-events: none;
  z-index: 10;
}

h1 {
  font-size: 2rem;
  margin-bottom: 0.5rem;
  text-shadow: 0 0 10px rgba(255, 215, 0, 0.5);
}

.legend {
  margin-top: 20px;
  background: rgba(0, 0, 0, 0.5);
  padding: 10px;
  border-radius: 8px;
  backdrop-filter: blur(5px);
}

.dot {
  display: inline-block;
  width: 10px;
  height: 10px;
  border-radius: 50%;
  margin-right: 5px;
}

.conservative { background: #00ccff; }
.radical { background: #ff3300; }
.observer { background: #00ff00; }
</style>
