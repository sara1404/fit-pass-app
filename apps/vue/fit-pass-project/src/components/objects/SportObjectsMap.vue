<script setup>

</script>
<template>
  <div ref="map-root" style="width: 100%; height: 100%">

  </div>
</template>

<script>
import View from 'ol/View'
import Map from 'ol/Map'
import TileLayer from 'ol/layer/Tile'
import OSM from 'ol/source/OSM'
import {Feature} from 'ol'
import Vector from 'ol/layer/Vector'
import SourceVector from 'ol/source/Vector'
import { fromLonLat } from 'ol/proj'
import Point from 'ol/geom/Point'
import {Style, Icon} from 'ol/style'
import 'ol/ol.css'
import {sportObjectsStore} from "../../stores/objects-store.js";
import {mapState} from "pinia";

export default {
  name: "SportObjectsMap",
  data: function() {
    return {
      sportObjectsStore: null,
    }
  },
  computed: {
    ...mapState(sportObjectsStore, ['sportObjects'])
  },
  mounted: async function(){
    this.sportObjectsStore = sportObjectsStore()
    await this.sportObjectsStore.getSportObjects({})
    let map = new Map({
      target: this.$refs['map-root'],
      layers: [
        new TileLayer({
          source: new OSM()
        }),
      ],

      view: new View({
        zoom: 0,
        center: [0, 0],
        constrainResolution: true
      }),
    })
    this.sportObjects.forEach(object => {
      console.log("adding")
      let {latitude, longitude}  = object.location
      let layer = this.createPin(latitude, longitude)
      map.addLayer(layer)
    })
  },

  methods: {
    createPin(latitude, longitude) {
      let iconStyle = new Style({
        image: new Icon({
          src: 'http://localhost:8000/imgs/marker.png'
        })
      })
      return new Vector({
        source: new SourceVector({
          features: [
            new Feature({
              geometry: new Point(fromLonLat([latitude, longitude]))
            })
          ]
        }),
        style: iconStyle
      });

    }
  }
}
</script>

<style scoped>


</style>