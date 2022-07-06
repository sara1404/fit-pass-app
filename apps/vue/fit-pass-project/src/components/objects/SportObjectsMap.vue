
<template>
  <div ref="map-root" style="width: 100%; height: 100%">

  </div>
</template>

<script>
import View from 'ol/View'
import Map from 'ol/Map'
import TileLayer from 'ol/layer/Tile'
import Vector from 'ol/layer/Vector'
import OSM from 'ol/source/OSM'
import SourceVector from 'ol/source/Vector'
import Point from 'ol/geom/Point'

import { Feature } from 'ol'
import { fromLonLat, toLonLat } from 'ol/proj'
import { Style, Icon, Text, Fill, Stroke } from 'ol/style'
import 'ol/ol.css'


import { sportObjectsStore } from "../../stores/objects-store.js";
import { mapState } from "pinia";

export default {
  name: "SportObjectsMap",
  props: {
    sportObjects: {
      type: Array,
      default: []
    }
  },
  data: function () {
    return {
      sportObjectsStore: null,
      map: null
    }
  },
  mounted: async function () {
    this.createMap()
    this.setAllLayers()
    this.addCoordinateClickListener()
  },

  methods: {
    createMap: function () {
      this.map = new Map({
        target: this.$refs['map-root'],
        layers: [],
        view: new View({
          zoom: 0,
          center: [0, 0],
          constrainResolution: true
        }),
      })
    },
    createPin(coords, objectName) {
      let iconStyle = new Style({
        image: new Icon({
          src: 'http://localhost:8000/imgs/marker.png'
        })
      })

      let labelStyle = new Style({
        text: new Text({
          font: '20px Calibri,sans-serif',
          overflow: true,
          fill: new Fill({
            color: '#000'
          }),
          stroke: new Stroke({
            color: '#fff',
            width: 10
          }),
          offsetX: 0,
          offsetY: -30
        })
      })

      let style = [iconStyle, labelStyle]

      return new Vector({
        source: new SourceVector({
          features: [
            new Feature({
              geometry: new Point(fromLonLat(coords))
            })
          ]
        }),
        style: function (feature) {
          labelStyle.getText().setText(objectName)
          return style
        }
      });
    },

    setAllLayers() {
      if (this.map == null) return;
      this.deleteAllLayers()
      this.map.addLayer(
        new TileLayer({
          source: new OSM()
        }),
      )
      this.sportObjects.forEach(object => {
        let { latitude, longitude } = object.location
        let layer = this.createPin([latitude, longitude], object.name)
        this.map.addLayer(layer)
      })
    },

    deleteAllLayers() {
      const layers = [...this.map.getLayers().getArray()]
      layers.forEach((layer) => {
        this.map.removeLayer(layer)
      })
    },
    addCoordinateClickListener() {
      this.map.on('click', (e) => {
        let coords = toLonLat(e.coordinate)
        let lon = coords[0]
        let lat = coords[1]
        this.$emit('dropped-pin', [lon, lat])
      })
    }


  },
  watch: {
    sportObjects: function (newValue) {
      this.setAllLayers()
    }
  }
}
</script>

<style scoped>
</style>