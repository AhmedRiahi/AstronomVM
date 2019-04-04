


var OperationPlotter = function(){

  var self = this;

  self.selectedNodes = new Array()

  self.displayProps = {
    width : "100%",
    height : 700,
  }

  self.init = function(scope){
    self.svg = d3.select("#canvas")
                .append("svg:svg")
                  .attr("width", self.displayProps.width)
                  .attr("height", self.displayProps.height)
                  .attr("pointer-events", "all");

    self.svg.append("svg:defs")
    .append("svg:marker")
    .attr("id", "triangle")
    .attr("refX", 30)
    .attr("refY", 4)
    .attr("markerWidth", 12)
    .attr("markerHeight", 12)
    .attr("orient", "auto")
    .append("path")
    .attr("d", "M0,0 V8 L8,4 Z")
    .style("fill", "black");

    self.steps = new Array();
    self.transitions = new Array();
    self.redraw()
    self.scope = scope;
    self.clickedNode = null;
    self.clickedLink = null;
    

  }

  self.redraw = function(){

    self.link = self.svg.selectAll(".link")
    .data(self.transitions)
    .enter()
    .append("svg:line")
    .attr('class','link')
    .attr("marker-end", "url(#triangle)")
    .on('dblclick',self.linkClicked)
    .on("mouseover", function(d,i){
      d3.select(this).classed('hovered-link',true);
    })
    .on("mouseout", function(d,i){
      d3.select(this).classed('hovered-link',false);
    })
    .attr("x1", function(d){
      var fromStep = self.findStepById(d.fromStep.id)
      return fromStep.graphicsProperties.x;
    })
    .attr("y1", function(d){
      var fromStep = self.findStepById(d.fromStep.id)
      return fromStep.graphicsProperties.y;
    })
    .attr("x2", function(d){
      var toStep = self.findStepById(d.toStep.id)
      return toStep.graphicsProperties.x;
    })
    .attr("y2", function(d){
      var toStep = self.findStepById(d.toStep.id)
      return toStep.graphicsProperties.y;
    })
    .style("fill", function(d, i) { return 'red' })

    self.link.exit().remove()

    self.node = self.svg.selectAll("g.node")
    .data(self.steps)
    .enter()
    .append("svg:g")
    .attr('class','node')
    .attr("transform", function(d){
      return 'translate('+d.graphicsProperties.x+','+d.graphicsProperties.y+')';
    }).call(
      d3.drag()
      .on("start", self.dragstarted)
      .on("drag", self.dragged)
      .on("end", self.dragended)
    ).on("mouseover", function(d,i){
      d3.select(this).select('.component-circle').classed('hovered-component',true);
    })
    .on("mouseout", function(d,i){
      d3.select(this).select('.component-circle').classed('hovered-component',false);
    })
    .on('dblclick',self.selectNode)
    .on('click',self.nodeClicked)



    var circles = self.node.append("svg:circle")
      .attr("r", 20)
      .attr('class','component-circle')
      .style("fill", function(d, i) { return '#93cfff' })
      
      

    circles.append("svg:image")
      .attr('x', -13)
      .attr('y', -13)
      .attr('width', 26)
      .attr('height', 26)
      .attr("xlink:href", "/image/file.png")

    self.node.exit().remove()
      

    self.node.append('svg:text')
    .text(function(d, i) {
        return d.name;
    })
    .attr("text-anchor", "middle")
    .attr("y", "38")
    .style("fill", "rgb(167, 167, 167)").style("font-family", "Arial").style("font-size", 12);
    
  }


  self.dragstarted=function (d) {
    d3.select(this).raise().classed("active", true);
  }

  self.dragged=function (d,i) {
    d3.select(this).attr("transform", 'translate('+d3.event.x+','+d3.event.y+')');
    d.graphicsProperties.x = d3.event.x; 
    d.graphicsProperties.y = d3.event.y;
    self.link.each(function(l, li) {
      if(l.fromStep.id == d.id){
        d3.select(this).attr("x1", d.graphicsProperties.x).attr("y1", d.graphicsProperties.y);
      }else{
        if(l.toStep.id == d.id){
          d3.select(this).attr("x2", d.graphicsProperties.x).attr("y2", d.graphicsProperties.y);
        }
      }
    })
    
  }

  self.dragended=function (d) {
    d3.select(this).classed("active", false);
  }

  self.drawStep = function(step){
    console.log("drawing new component")
    self.steps.push(step);
    self.redraw()
  }

  self.drawOperation = function(operation){
    console.log('draw operation');
    self.steps = operation.steps;
    self.transitions = operation.transitions;
    self.redraw();
  }


  self.selectNode = function(d){
    console.log(d)
    if(d.isSelected == undefined || !d.isSelected){
      d.isSelected = true;
      d3.select(this).select('.component-circle').classed('selectedNode',true)
      self.selectedNodes.push(d)
    }else{
      d.isSelected = false;
      d3.select(this).select('.component-circle').classed('selectedNode',false)
      var index = self.selectedNodes.indexOf(d)
      self.selectedNodes.splice(index,1)
    }
  }

  self.nodeImageDblClicked = function(d){
    console.log(d)
    if(d.isSelected == undefined || !d.isSelected){
      d.isSelected = true;
      d3.select(this.parentNode).select('circle').classed('selectedNode',true)
      self.selectedNodes.push(d)
    }else{
      d.isSelected = false;
      d3.select(this.parentNode).select('circle').classed('selectedNode',false)
      var index = self.selectedNodes.indexOf(d)
      self.selectedNodes.splice(index,1)
    }
  }

  self.nodeClicked = function(d){
    console.log('click')
    console.log(d)
    if(self.clickedNode != null){
      d3.select(self.clickedNode).classed('clickedNode',false)
    }
    self.clickedNode = this;
    d3.select(self.clickedNode).classed('clickedNode',true)
    self.scope.selectStep(d);
  }

  self.linkClicked = function(d){
    console.log(d)
    if(self.clickedLink != null){
      d3.select(self.clickedLink).classed('clicked-link',false)
    }
    self.clickedLink = this;
    d3.select(self.clickedLink).classed('clicked-link',true)
    self.scope.transitionClicked(d);
  }


  self.getSelectedNodes = function(){
    console.log(self.selectedNodes)
    return self.selectedNodes;
  }


  self.findStepById = function(id){
    for(var i=0;i<self.steps.length;i++){
      if(self.steps[i].id == id){
        return self.steps[i];
      }
    }
  }
}