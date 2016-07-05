var MObject = function MObject () {
        this.m = function() {
            console.log( "called m on MObject from ObjectExtension.js")
        }
    }

module.exports = {
    "MObject": MObject
}