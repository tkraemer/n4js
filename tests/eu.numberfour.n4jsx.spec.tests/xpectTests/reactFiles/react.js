//Mock implementation for React.createElement
module.exports.createElement = function(type, props, ...children) {
	return {type: type, props: props, children: children};
}

//Implement React.Component.constructor as function prototype
var Component = function(props) {
	this.props = props;
}
Component.prototype.constructor = Component;
module.exports.Component = Component;