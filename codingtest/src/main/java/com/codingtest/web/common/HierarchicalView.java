package com.codingtest.web.common;

import java.io.Serializable;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.bean.ViewScoped;

import org.primefaces.model.diagram.DefaultDiagramModel;
import org.primefaces.model.diagram.DiagramModel;
import org.primefaces.model.diagram.Element;
import org.primefaces.model.diagram.Connection;
import org.primefaces.model.diagram.connector.StraightConnector;
import org.primefaces.model.diagram.endpoint.BlankEndPoint;
import org.primefaces.model.diagram.endpoint.DotEndPoint;
import org.primefaces.model.diagram.endpoint.EndPoint;
import org.primefaces.model.diagram.endpoint.EndPointAnchor;

@ManagedBean(name = "diagramHierarchicalView")
@ViewScoped
public class HierarchicalView implements Serializable {

	private static final long serialVersionUID = -4592207607086367285L;
	private DefaultDiagramModel model;

	@PostConstruct
	public void init() {
		model = new DefaultDiagramModel();
		model.setMaxConnections(-1);

		Element ceo = new Element("Master Hierarchy", "1em", "40em");
		ceo.addEndPoint(createEndPoint(EndPointAnchor.TOP_RIGHT));
		ceo.addEndPoint(createEndPoint(EndPointAnchor.BOTTOM));
		model.addElement(ceo);

		Element fin = new Element("CenterStore", "30em", "2em");
		fin.addEndPoint(createEndPoint(EndPointAnchor.CONTINUOUS));
		
		model.addElement(fin);

		Element fin1 = new Element("Department", "55em", "2em");
		//fin1.addEndPoint(createEndPoint(EndPointAnchor.CONTINUOUS_LEFT));
		fin1.addEndPoint(createEndPoint(EndPointAnchor.CONTINUOUS));
		model.addElement(fin1);
		
		Element fin2 = new Element("Category", "85em", "2em");
		fin2.addEndPoint(createEndPoint(EndPointAnchor.CONTINUOUS));
		model.addElement(fin2);
		
		Element fin3 = new Element("SubCategory", "115em", "2em");
		fin3.addEndPoint(createEndPoint(EndPointAnchor.CONTINUOUS));
		model.addElement(fin3);
		
		Element cen = new Element("Center", "35em", "52em");
		cen.addEndPoint(createEndPoint(EndPointAnchor.CONTINUOUS));
		model.addElement(cen);
		
		Element cen1 = new Element("Dairy", "75em", "32em");
		cen1.addEndPoint(createEndPoint(EndPointAnchor.CONTINUOUS));
		model.addElement(cen1);
		
		Element cen2 = new Element("Frozen", "75em", "42em");
		cen2.addEndPoint(createEndPoint(EndPointAnchor.CONTINUOUS));
		model.addElement(cen2);
		
		Element cen3 = new Element("GM", "75em", "48em");
		cen3.addEndPoint(createEndPoint(EndPointAnchor.CONTINUOUS));
		model.addElement(cen3);
		
		Element cen4 = new Element("Grocery", "75em", "55em");
		cen4.addEndPoint(createEndPoint(EndPointAnchor.CONTINUOUS));
		model.addElement(cen4);
		
		Element gm = new Element("Audio Video", "95em", "32em");
		gm.addEndPoint(createEndPoint(EndPointAnchor.CONTINUOUS));
		model.addElement(gm);
		
		Element gm1 = new Element("Audio", "125em", "12em");
		gm1.addEndPoint(createEndPoint(EndPointAnchor.CONTINUOUS));
		model.addElement(gm1);
		
		Element gm2 = new Element("Video DVD", "125em", "52em");
		gm2.addEndPoint(createEndPoint(EndPointAnchor.CONTINUOUS));
		model.addElement(gm2);
		
		Element per = new Element("Perimeter", "35em", "62em");
		per.addEndPoint(createEndPoint(EndPointAnchor.CONTINUOUS));
		model.addElement(per);

		StraightConnector connector = new StraightConnector();
		connector.setPaintStyle("{strokeStyle:'#404a4e', lineWidth:3}");
		connector.setHoverPaintStyle("{strokeStyle:'#20282b'}");

		model.connect(new Connection(ceo.getEndPoints().get(0), fin.getEndPoints().get(0)));
		model.connect(new Connection(fin.getEndPoints().get(0),fin1.getEndPoints().get(0)));
		model.connect(new Connection(fin1.getEndPoints().get(0),fin2.getEndPoints().get(0)));
		model.connect(new Connection(fin2.getEndPoints().get(0),fin3.getEndPoints().get(0)));
		model.connect(new Connection(ceo.getEndPoints().get(1),cen.getEndPoints().get(0)));
		model.connect(new Connection(cen.getEndPoints().get(0),cen1.getEndPoints().get(0)));
		model.connect(new Connection(cen.getEndPoints().get(0),cen2.getEndPoints().get(0)));
		model.connect(new Connection(cen.getEndPoints().get(0),cen3.getEndPoints().get(0)));
		model.connect(new Connection(cen.getEndPoints().get(0),cen4.getEndPoints().get(0)));
		model.connect(new Connection(cen3.getEndPoints().get(0),gm.getEndPoints().get(0)));
		model.connect(new Connection(gm.getEndPoints().get(0),gm1.getEndPoints().get(0)));
		model.connect(new Connection(gm.getEndPoints().get(0),gm2.getEndPoints().get(0)));
		model.connect(new Connection(ceo.getEndPoints().get(1),per.getEndPoints().get(0)));
		// CFO
		/*
		 * Element cfo = new Element("CFO", "10em", "18em");
		 * cfo.addEndPoint(createEndPoint(EndPointAnchor.TOP));
		 * cfo.addEndPoint(createEndPoint(EndPointAnchor.BOTTOM));
		 * 
		 * Element fin = new Element("FIN", "5em", "30em");
		 * fin.addEndPoint(createEndPoint(EndPointAnchor.TOP_RIGHT));
		 * 
		 * Element pur = new Element("PUR", "20em", "30em");
		 * pur.addEndPoint(createEndPoint(EndPointAnchor.TOP));
		 * 
		 * model.addElement(cfo); model.addElement(fin); model.addElement(pur);
		 * 
		 * //CTO Element cto = new Element("CTO", "40em", "1em");
		 * cto.addEndPoint(createEndPoint(EndPointAnchor.TOP));
		 * cto.addEndPoint(createEndPoint(EndPointAnchor.BOTTOM));
		 * 
		 * Element dev = new Element("DEV", "35em", "30em");
		 * dev.addEndPoint(createEndPoint(EndPointAnchor.TOP));
		 * 
		 * Element tst = new Element("TST", "50em", "30em");
		 * tst.addEndPoint(createEndPoint(EndPointAnchor.TOP));
		 * 
		 * model.addElement(cto); model.addElement(dev); model.addElement(tst);
		 * 
		 * StraightConnector connector = new StraightConnector();
		 * connector.setPaintStyle("{strokeStyle:'#404a4e', lineWidth:3}");
		 * connector.setHoverPaintStyle("{strokeStyle:'#20282b'}");
		 * 
		 * //connections model.connect(new Connection(ceo.getEndPoints().get(0),
		 * cfo.getEndPoints().get(0), connector)); model.connect(new
		 * Connection(ceo.getEndPoints().get(0), cto.getEndPoints().get(0),
		 * connector)); model.connect(new Connection(cfo.getEndPoints().get(1),
		 * fin.getEndPoints().get(0), connector)); model.connect(new
		 * Connection(cfo.getEndPoints().get(1), pur.getEndPoints().get(0),
		 * connector)); model.connect(new Connection(cto.getEndPoints().get(1),
		 * dev.getEndPoints().get(0), connector)); model.connect(new
		 * Connection(cto.getEndPoints().get(1), tst.getEndPoints().get(0),
		 * connector));
		 */
	}

	private EndPoint createEndPoint(EndPointAnchor anchor) {
		DotEndPoint endPoint = new DotEndPoint(anchor);
		endPoint.setStyle("{fillStyle:'#404a4e'}");
		endPoint.setHoverStyle("{fillStyle:'#20282b'}");

		return endPoint;
	}

	public DiagramModel getModel() {
		return model;
	}
}
