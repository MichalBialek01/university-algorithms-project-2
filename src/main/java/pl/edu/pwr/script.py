import json
import networkx as nx
import matplotlib.pyplot as plt
import glob
import os

def draw_graph_with_paths(edges, source, filename):
    G = nx.DiGraph()

    # Dodawanie węzłów przed krawędziami
    nodes = set()
    for edge in edges:
        nodes.add(edge[0])
        nodes.add(edge[1])

    for node in nodes:
        G.add_node(node)

    # Debugging: wydrukuj węzły
    print(f"Węzły w grafie: {G.nodes()}")

    for u, v, weight in edges:
        G.add_edge(u, v, weight=weight)

    # Obliczanie najkrótszych ścieżek
    try:
        path_lengths = nx.single_source_dijkstra_path_length(G, source)
        paths = nx.single_source_dijkstra_path(G, source)
    except nx.NetworkXError as e:
        print(f"Błąd: {e}")
        return

    plt.figure(figsize=(40,40))  # Ustawienie rozmiaru figury

    pos = nx.spring_layout(G)  # Pozycje dla wizualizacji
    nx.draw_networkx_nodes(G, pos, node_color='lightblue', node_size=500)
    nx.draw_networkx_labels(G, pos)
    nx.draw_networkx_edges(G, pos, edge_color='gray')
    edge_labels = nx.get_edge_attributes(G, 'weight')
    nx.draw_networkx_edge_labels(G, pos, edge_labels=edge_labels)

    # Rysowanie najkrótszych ścieżek czerwonym kolorem
    for target, path in paths.items():
        path_edges = list(zip(path[:-1], path[1:]))
        nx.draw_networkx_edges(G, pos, edgelist=path_edges, edge_color='red', width=2)

    plt.title(f'Graf z najkrótszymi ścieżkami od wierzchołka {source} dla pliku {filename}')
    plt.show()

# Znalezienie wszystkich plików JSON w bieżącym katalogu
json_files = glob.glob("graphData_*.json")

# Przetwarzanie każdego pliku JSON
for file_path in json_files:
    with open(file_path, 'r') as file:
        data = json.load(file)

    nodes = data['nodes']
    edges = data['edges']
    if not edges:
        print(f"Plik {file_path} nie zawiera krawędzi.")
        continue

    source = data['shortestPath'][0] if data['shortestPath'] else 0  # Zakładamy, że źródło jest pierwszym elementem w shortestPath, lub 0 jeśli brak ścieżki

    # Konwertowanie krawędzi do formatu akceptowanego przez funkcję draw_graph_with_paths
    edge_list = [(edge['source'], edge['target'], edge['weight']) for edge in edges]

    # Debugging: wydrukuj krawędzie
    print(f"Przetwarzanie pliku: {file_path}")
    print(f"Krawędzie: {edge_list}")
    print(f"Źródło: {source}")

    draw_graph_with_paths(edge_list, source, os.path.basename(file_path))
